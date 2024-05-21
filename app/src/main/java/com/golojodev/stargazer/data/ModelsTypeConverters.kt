package com.golojodev.stargazer.data

import androidx.room.TypeConverter
import com.golojodev.library.style.ThemeState
import com.golojodev.stargazer.domain.models.LaunchType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ModelsTypeConverters {
    @TypeConverter
    fun convertTagsToString(tags: List<String>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun convertStringToTags(tags: String): List<String> {
        return Json.decodeFromString(tags)
    }

    @TypeConverter
    fun convertThemeStateToString(themeState: ThemeState): String {
        return Json.encodeToString(themeState)
    }

    @TypeConverter
    fun convertStringToThemeState(themeState: String): ThemeState {
        return Json.decodeFromString(themeState)
    }

    @TypeConverter
    fun convertLaunchTypeToString(launchType: LaunchType): String {
        return when (launchType) {
            LaunchType.Previous -> "previous"
            LaunchType.Upcoming -> "upcoming"
        }
    }

    @TypeConverter
    fun convertStringToLaunchType(launchType: String): LaunchType {
        return when (launchType) {
            "previous" -> LaunchType.Previous
            else -> LaunchType.Upcoming
        }
    }
}