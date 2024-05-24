package com.golojodev.stargazer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.golojodev.stargazer.data.entities.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StargazerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(launchEntity: LaunchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launches: List<LaunchEntity>)

    @Query("SELECT * FROM Launch")
    fun getLaunches(): Flow<List<LaunchEntity>>

    @Update
    suspend fun update(modelEntity: LaunchEntity)

    @Query("SELECT * FROM Launch WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<LaunchEntity>>
}