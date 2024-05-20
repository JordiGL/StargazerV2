package com.golojodev.stargazer.domain.repositories

import com.golojodev.stargazer.data.StargazerDao
import com.golojodev.stargazer.data.entities.LaunchEntity
import com.golojodev.stargazer.domain.models.Launch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.Date

class LaunchRepositoryImpl(
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
                            status = launchEntity.status,
                            net = Date(launchEntity.net),
                            pad = launchEntity.pad,
                            mission = launchEntity.mission,
                            rocket = launchEntity.rocket,
                            launchServiceProvider = launchEntity.launchServiceProvider,
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
            val response: Response<List<Launch>> = Response.success(
                listOf(
                    Launch(
                        id = "1",
                        name = "Falcon 9",
                        status = "Success",
                        net = Date(),
                        pad = "Launch Pad 1",
                        mission = "Starlink",
                        rocket = "Falcon 9 Block 5",
                        launchServiceProvider = "SpaceX"
                    ),
                    Launch(
                        id = "2",
                        name = "Ariane 5",
                        status = "Failure",
                        net = Date(),
                        pad = "Launch Pad 2",
                        mission = "James Webb Space Telescope",
                        rocket = "Ariane 5 ECA",
                        launchServiceProvider ="Arianespace"
                    ),
                    Launch(
                        id = "3",
                        name = "Long March 5",
                        status = "In progress",
                        net = Date(),
                        pad = "Launch Pad 3",
                        mission = "Chang'e 5",
                        rocket = "Long March 5",
                        launchServiceProvider = "China National Space Administration"
                    )
                )
            )
            if (response.isSuccessful) {
                response.body()!!.map { launch ->
                    launchDao.insert(
                        LaunchEntity(
                            id = launch.id,
                            name = launch.name,
                            status = launch.status,
                            net = launch.net.time,
                            pad = launch.pad,
                            mission = launch.mission,
                            rocket = launch.rocket,
                            launchServiceProvider = launch.launchServiceProvider,
                            isFavorite = launch.isFavorite
                        )
                    )
                }
                flowOf(response.body()!!)
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
                    status = launch.status,
                    net = launch.net.time,
                    pad = launch.pad,
                    mission = launch.mission,
                    rocket = launch.rocket,
                    launchServiceProvider = launch.launchServiceProvider,
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
                            status = launchEntity.status,
                            net = Date(launchEntity.net),
                            pad = launchEntity.pad,
                            mission = launchEntity.mission,
                            rocket = launchEntity.rocket,
                            launchServiceProvider = launchEntity.launchServiceProvider,
                            isFavorite = launchEntity.isFavorite
                        )
                    }
                }
        }
    }

}