package com.golojodev.stargazer.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ModelEntity::class, ThemeStateEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(ModelsTypeConverters::class)
abstract class ModelDatabase : RoomDatabase() {
    abstract fun modelDao(): ModelDao

    abstract fun themeStateDao(): ThemeStateDao
}