package com.golojodev.stargazer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.golojodev.stargazer.domain.models.LaunchType

@Entity(tableName = "Launch")
data class LaunchEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val statusName: String,
    val statusDescription: String,
    val net: String,
    val padName: String,
    val padLocation: String,
    val padCountryCode: String,
    val missionName: String,
    val rocketName: String,
    val rocketFamily: String,
    val rocketVariant: String,
    val launchServiceProvider: String,
    val launchType: LaunchType,
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean = false
)
