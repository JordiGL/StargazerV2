package com.golojodev.stargazer.data.factory

import com.golojodev.stargazer.data.entities.LaunchEntity
import com.golojodev.stargazer.domain.models.Agency
import com.golojodev.stargazer.domain.models.Configuration
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.models.LaunchType
import com.golojodev.stargazer.domain.models.Location
import com.golojodev.stargazer.domain.models.Mission
import com.golojodev.stargazer.domain.models.Pad
import com.golojodev.stargazer.domain.models.Rocket
import com.golojodev.stargazer.domain.models.Status

fun List<Launch>.mapToLaunchEntities(launchType: LaunchType): List<LaunchEntity> {
    return map { launch ->
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
            launchType = launchType,
            isFavorite = launch.isFavorite
        )
    }
}

fun Launch.toLaunchEntity(): LaunchEntity {
    return LaunchEntity(
        id = id,
        name = name,
        statusName = status.name,
        statusDescription = status.description,
        net = net,
        padName = pad.name,
        padLocation = pad.location.name,
        padCountryCode = pad.location.countryCode,
        missionName = mission.name,
        rocketName = rocket.configuration.name,
        rocketFamily = rocket.configuration.family,
        rocketVariant = rocket.configuration.variant,
        launchServiceProvider = launchServiceProvider.name,
        launchType = launchType,
        isFavorite = isFavorite
    )
}

fun LaunchEntity.toLaunch(): Launch {
    return Launch(
        id = id,
        name = name,
        status = Status(statusName, statusDescription),
        net = net,
        pad = Pad(padName, Location(padLocation, padCountryCode)),
        mission = Mission(missionName),
        rocket = Rocket(
            configuration = Configuration(rocketName, rocketFamily, rocketVariant)
        ),
        launchServiceProvider = Agency(launchServiceProvider),
        launchType = launchType,
        isFavorite = isFavorite
    )
}