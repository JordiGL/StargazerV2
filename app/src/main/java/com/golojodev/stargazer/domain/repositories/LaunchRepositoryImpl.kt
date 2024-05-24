package com.golojodev.stargazer.domain.repositories

import com.golojodev.stargazer.data.StargazerDao
import com.golojodev.stargazer.data.factory.LaunchFactory
import com.golojodev.stargazer.data.factory.mapToLaunchEntities
import com.golojodev.stargazer.data.factory.toLaunch
import com.golojodev.stargazer.data.factory.toLaunchEntity
import com.golojodev.stargazer.data.service.ServiceAPI
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.models.LaunchType
import com.golojodev.stargazer.domain.models.LaunchesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import retrofit2.Response

class LaunchRepositoryImpl(
    private val serviceAPI: ServiceAPI,
    private val dispatcher: CoroutineDispatcher,
    private val launchDao: StargazerDao
) : LaunchRepository {
    override suspend fun getlaunches(): Flow<List<Launch>> {
        return withContext(dispatcher) {
            launchDao.getLaunches()
                .map { it.map { launchEntity -> launchEntity.toLaunch() } }
                .onEach {
                    if (it.isEmpty()) {
                        fetchRemoteLaunches()
                    }
                }
        }
    }

    override suspend fun fetchRemoteLaunches(): Flow<List<Launch>> = withContext(dispatcher) {
        flowOf(
            fetchAndInsertLaunches(LaunchType.Upcoming, serviceAPI::fetchUpcomingLaunches),
            fetchAndInsertLaunches(LaunchType.Previous, serviceAPI::fetchPreviousLaunches)
        )
    }


    private suspend fun fetchAndInsertLaunches(
        launchType: LaunchType,
        fetchFunction: suspend () -> Response<LaunchesResponse>
    ) : List<Launch> {
        val response = fetchFunction()
        return if (response.isSuccessful) {
            response.body()!!.results.mapToLaunchEntities(launchType).also {
                launchDao.insertAll(it)
            }.map { it.toLaunch() }
        } else {
            emptyList()
        }
    }

    override suspend fun updateLaunch(launch: Launch) {
        withContext(dispatcher) {
            launchDao.update(launch.toLaunchEntity())
        }
    }

    override suspend fun getFavorites(): Flow<List<Launch>> {
        return withContext(dispatcher) {
            launchDao.getFavorites().map { launchesCached ->
                launchesCached.map { it.toLaunch() }
            }
        }
    }

    suspend fun getUpcomingLaunches(): Flow<List<Launch>>{
        return withContext(dispatcher) {
            val launches = serviceAPI.fetchUpcomingLaunches()
            if (launches.isSuccessful) {
                launches.body()!!.results.mapToLaunchEntities(LaunchType.Upcoming).let {
                    launchDao.insertAll(it)
                }
                flowOf(launches.body()!!.results)
            } else {
                flowOf(emptyList())
            }
        }
    }

    suspend fun getPreviousLaunches(): Flow<List<Launch>>{
        return withContext(dispatcher) {
            val launches = serviceAPI.fetchPreviousLaunches()
            if (launches.isSuccessful) {
                launches.body()!!.results.mapToLaunchEntities(LaunchType.Previous).let {
                    launchDao.insertAll(it)
                }
                flowOf(launches.body()!!.results)
            } else {
                flowOf(emptyList())
            }
        }
    }

    suspend fun getFakeLaunches(): Flow<List<Launch>>{
        return withContext(dispatcher) {
            flowOf(LaunchFactory.getSuccesResponse().body()!!.results)
        }
    }
}