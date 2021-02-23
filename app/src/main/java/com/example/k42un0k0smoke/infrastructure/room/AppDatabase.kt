package com.example.k42un0k0smoke.infrastructure.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuitResultDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quitResultDao(): QuitResultDao
}