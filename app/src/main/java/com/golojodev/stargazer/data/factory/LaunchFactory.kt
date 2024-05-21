package com.golojodev.stargazer.data.factory

import com.golojodev.stargazer.domain.models.Agency
import com.golojodev.stargazer.domain.models.Configuration
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.models.LaunchType
import com.golojodev.stargazer.domain.models.Location
import com.golojodev.stargazer.domain.models.Mission
import com.golojodev.stargazer.domain.models.Pad
import com.golojodev.stargazer.domain.models.Rocket
import com.golojodev.stargazer.domain.models.Status

object LaunchFactory {
    fun createLaunches() = listOf(
        Launch(
            id = "e4fc3a94-8772-4f1b-bac7-8355e188ee13",
            name = "Kuaizhou-11 | Wuhan-1 and others",
            status = Status(name = "Launch Successful", description = "The launch vehicle successfully inserted its payload(s) into the target orbit(s)."),
            net = "2024-05-21T04:15:00Z",
            pad = Pad(name = "Launch Area 95A", Location(name = "Jiuquan Satellite Launch Center, People's Republic of China", countryCode = "CHN")),
            mission = Mission(name = "Wuhan-1 and others"),
            rocket = Rocket(configuration = Configuration(name = "Kuaizhou-11", family = "Kuaizhou", variant = "Kuaizhou-11")),
            launchServiceProvider = Agency(name = "ExPace"),
            launchType = LaunchType.Upcoming,
            isFavorite = false
        ),
        Launch(
            id = "5797838e-873f-427c-a969-f551602f288f",
            name = "Long March 2D | Gaofen-16",
            status = Status(name = "Launch Successful", description = "The launch vehicle successfully inserted its payload(s) into the target orbit(s)."),
            net = "2024-05-20T03:00:00Z",
            pad = Pad(name = "Launch Complex 9", Location(name = "Taiyuan Satellite Launch Center, People's Republic of China", countryCode = "CHN")),
            mission = Mission(name = "Gaofen-16"),
            rocket = Rocket(configuration = Configuration(name = "Long March 2D", family = "Long March 2", variant = "Long March 2D")),
            launchServiceProvider = Agency(name = "China Aerospace Science and Technology Corporation"),
            launchType = LaunchType.Previous,
            isFavorite = false
        ),
        Launch(
            id = "d8b8d82b-f18a-43f0-882d-9533e88c504b",
            name = "Soyuz-2.1a | Progress MS-23",
            status = Status(name = "Launch Successful", description = "The launch vehicle successfully inserted its payload(s) into the target orbit(s)."),
            net = "2024-05-19T11:30:00Z",
            pad = Pad(name = "Site 31/6", Location(name = "Baikonur Cosmodrome, Kazakhstan", countryCode = "KAZ")),
            mission = Mission(name = "Progress MS-23"),
            rocket = Rocket(configuration = Configuration(name = "Soyuz-2.1a", family = "Soyuz-2", variant = "Soyuz-2.1a")),
            launchServiceProvider = Agency(name = "Roscosmos"),
            launchType = LaunchType.Previous,
            isFavorite = false
        ),
        Launch(
            id = "e4fc3a94-8772-4f1b-bac7-8355e188ee13",
            name = "Kuaizhou-11 | Wuhan-1 and others",
            status = Status(name = "Launch Successful", description = "The launch vehicle successfully inserted its payload(s) into the target orbit(s)."),
            net = "2024-05-21T04:15:00Z",
            pad = Pad(name = "Launch Area 95A", Location(name = "Jiuquan Satellite Launch Center, People's Republic of China", countryCode = "CHN")),
            mission = Mission(name = "Wuhan-1 and others"),
            rocket = Rocket(configuration = Configuration(name = "Kuaizhou-11", family = "Kuaizhou", variant = "Kuaizhou-11")),
            launchServiceProvider = Agency(name = "ExPace"),
            launchType = LaunchType.Upcoming,
            isFavorite = false
        )
    )
}