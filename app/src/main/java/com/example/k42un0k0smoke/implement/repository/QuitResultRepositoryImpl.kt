package com.example.k42un0k0smoke.implement.repository

import androidx.annotation.WorkerThread
import com.example.k42un0k0smoke.infrastructure.room.QuitResultDao
import com.example.k42un0k0smoke.infrastructure.room.QuitResultDto
import com.example.k42un0k0smoke.model.quit_result.QuitResult
import com.example.k42un0k0smoke.repository.QuitResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuitResultRepositoryImpl @Inject constructor(private val quitResultDao: QuitResultDao) :
    QuitResultRepository {
    override val allQuitResults: Flow<List<QuitResult>> =
        quitResultDao.getAll().map { list -> list.map { it.toModel() } }

    @WorkerThread
    override suspend fun insert(quitResult: QuitResult) {
        quitResultDao.insert(QuitResultDto.fromModel(quitResult))
    }
}