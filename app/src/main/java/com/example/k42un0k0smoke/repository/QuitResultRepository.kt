package com.example.k42un0k0smoke.repository

import com.example.k42un0k0smoke.model.QuitResult
import kotlinx.coroutines.flow.Flow

interface QuitResultRepository {
    val allQuitResults: Flow<List<QuitResult>>
    suspend fun insert(quitResult: QuitResult)
}