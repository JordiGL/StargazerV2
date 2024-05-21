package com.golojodev.stargazer.domain.repositories

import com.golojodev.stargazer.data.StargazerDao
import com.golojodev.stargazer.data.entities.LaunchEntity
import com.golojodev.stargazer.data.factory.LaunchFactory
import com.golojodev.stargazer.data.service.ServiceAPI
import com.golojodev.stargazer.domain.models.Agency
import com.golojodev.stargazer.domain.models.Configuration
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.models.LaunchesResponse
import com.golojodev.stargazer.domain.models.Location
import com.golojodev.stargazer.domain.models.Mission
import com.golojodev.stargazer.domain.models.Pad
import com.golojodev.stargazer.domain.models.Rocket
import com.golojodev.stargazer.domain.models.Status
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
                .map { launchesCatched ->
                    launchesCatched.map { launchEntity ->
                        Launch(
                            id = launchEntity.id,
                            name = launchEntity.name,
                            status = Status(name = launchEntity.statusName, description = launchEntity.statusDescription),
                            net = launchEntity.net,
                            pad = Pad(name = launchEntity.padName, Location(name = launchEntity.padLocation, countryCode = launchEntity.padCountryCode)),
                            mission = Mission(name = launchEntity.missionName),
                            rocket = Rocket(configuration = Configuration(name = launchEntity.rocketName, family = launchEntity.rocketFamily, variant = launchEntity.rocketVariant)),
                            launchServiceProvider = Agency(name = launchEntity.launchServiceProvider),
                            launchType = launchEntity.launchType,
                            isFavorite = launchEntity.isFavorite
                        )
                    }
                }
                .onEach {
                    if (it.isEmpty()) {
                        fetchRemoteLaunches()
                    }
                }
        }
    }

    override suspend fun fetchRemoteLaunches(): Flow<List<Launch>> {
        return withContext(dispatcher) {
            val launches = serviceAPI.fetchUpcomingLaunches()
            if (launches.isSuccessful) {
                launches.body()!!.results.map { launch ->
                    launchDao.insert(
                        LaunchEntity(
                            id = launch.id,
                            name = launch.name,
                            statusName = launch.status.name,
                            statusDescription = launch.status.description,
                            net = launch.net,
                            padName = launch.pad.name,
                            padLocation = launch.pad.location.name,
                            padCountryCode = launch.pad.location.countryCode,
                            missionName = launch.mission.name,
                            rocketName = launch.rocket.configuration.name,
                            rocketFamily = launch.rocket.configuration.family,
                            rocketVariant = launch.rocket.configuration.variant,
                            launchServiceProvider = launch.launchServiceProvider.name,
                            launchType = launch.launchType,
                            isFavorite = launch.isFavorite
                        )
                    )
                }
                flowOf(launches.body()!!.results)
            } else {
                flowOf(emptyList())
            }
        }
    }

    override suspend fun updateLaunch(launch: Launch) {
        withContext(dispatcher) {
            launchDao.update(
                LaunchEntity(
                    id = launch.id,
                    name = launch.name,
                    statusName = launch.status.name,
                    statusDescription = launch.status.description,
                    net = launch.net,
                    padName = launch.pad.name,
                    padLocation = launch.pad.location.name,
                    padCountryCode = launch.pad.location.countryCode,
                    missionName = launch.mission.name,
                    rocketName = launch.rocket.configuration.name,
                    rocketFamily = launch.rocket.configuration.family,
                    rocketVariant = launch.rocket.configuration.variant,
                    launchServiceProvider = launch.launchServiceProvider.name,
                    launchType = launch.launchType,
                    isFavorite = launch.isFavorite
                )
            )
        }
    }

    override suspend fun getFavorites(): Flow<List<Launch>> {
        return withContext(dispatcher) {
            launchDao.getFavorites()
                .map { launchesCached ->
                    launchesCached.map { launchEntity ->
                        Launch(
                            id = launchEntity.id,
                            name = launchEntity.name,
                            status = Status(name = launchEntity.statusName, description = launchEntity.statusDescription),
                            net = launchEntity.net,
                            pad = Pad(name = launchEntity.padName, Location(name = launchEntity.padLocation, countryCode = launchEntity.padCountryCode)),
                            mission = Mission(name = launchEntity.missionName),
                            rocket = Rocket(configuration = Configuration(name = launchEntity.rocketName, family = launchEntity.rocketFamily, variant = launchEntity.rocketVariant)),
                            launchServiceProvider = Agency(name = launchEntity.launchServiceProvider),
                            isFavorite = launchEntity.isFavorite
                        )
                    }
                }
        }
    }

    suspend fun getUpcomingLaunches(): Flow<List<Launch>>{
        return withContext(dispatcher) {
            val launches = serviceAPI.fetchUpcomingLaunches()
            if (launches.isSuccessful) {
                launches.body()!!.results.map { launch ->
                    launchDao.insert(
                        LaunchEntity(
                            id = launch.id,
                            name = launch.name,
                            statusName = launch.status.name,
                            statusDescription = launch.status.description,
                            net = launch.net,
                            padName = launch.pad.name,
                            padLocation = launch.pad.location.name,
                            padCountryCode = launch.pad.location.countryCode,
                            missionName = launch.mission.name,
                            rocketName = launch.rocket.configuration.name,
                            rocketFamily = launch.rocket.configuration.family,
                            rocketVariant = launch.rocket.configuration.variant,
                            launchServiceProvider = launch.launchServiceProvider.name,
                            launchType = launch.launchType,
                            isFavorite = launch.isFavorite
                        )
                    )
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
                launches.body()!!.results.map { launch ->
                    launchDao.insert(
                        LaunchEntity(
                            id = launch.id,
                            name = launch.name,
                            statusName = launch.status.name,
                            statusDescription = launch.status.description,
                            net = launch.net,
                            padName = launch.pad.name,
                            padLocation = launch.pad.location.name,
                            padCountryCode = launch.pad.location.countryCode,
                            missionName = launch.mission.name,
                            rocketName = launch.rocket.configuration.name,
                            rocketFamily = launch.rocket.configuration.family,
                            rocketVariant = launch.rocket.configuration.variant,
                            launchServiceProvider = launch.launchServiceProvider.name,
                            launchType = launch.launchType,
                            isFavorite = launch.isFavorite
                        )
                    )
                }
                flowOf(launches.body()!!.results)
            } else {
                flowOf(emptyList())
            }
        }
    }

    suspend fun getFakeLaunches(): Flow<List<Launch>>{
        return withContext(dispatcher) {
            val fakeLaunches = LaunchFactory.createLaunches()
            val response: Response<LaunchesResponse> = Response.success(
                LaunchesResponse(
                    count = fakeLaunches.size,
                    results = fakeLaunches,
                    previous = null,
                    next = ""
                )
            )
            if (response.isSuccessful) {
                response.body()!!.results.map { launch ->
                    launchDao.insert(
                        LaunchEntity(
                            id = launch.id,
                            name = launch.name,
                            statusName = launch.status.name,
                            statusDescription = launch.status.description,
                            net = launch.net,
                            padName = launch.pad.name,
                            padLocation = launch.pad.location.name,
                            padCountryCode = launch.pad.location.countryCode,
                            missionName = launch.mission.name,
                            rocketName = launch.rocket.configuration.name,
                            rocketFamily = launch.rocket.configuration.family,
                            rocketVariant = launch.rocket.configuration.variant,
                            launchServiceProvider = launch.launchServiceProvider.name,
                            launchType = launch.launchType,
                            isFavorite = launch.isFavorite
                        )
                    )
                }
                flowOf(response.body()!!.results)
            } else {
                flowOf(emptyList())
            }
        }
    }
}