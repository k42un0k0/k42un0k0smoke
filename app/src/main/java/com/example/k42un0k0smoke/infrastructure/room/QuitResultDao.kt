package com.example.k42un0k0smoke.infrastructure.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuitResultDao {
    @Query("select * from result_dto")
    fun getAll(): Flow<List<QuitResultDto>>

    @Insert
    fun insert(quitResultDto: QuitResultDto)

    @Delete
    fun delete(quitResultDto: QuitResultDto)
}