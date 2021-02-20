package com.example.k42un0k0smoke.modules.quit_results

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.k42un0k0smoke.model.QuitResult
import com.example.k42un0k0smoke.repository.QuitResultRepository
import javax.inject.Inject

class QuitResultsViewModel @Inject constructor(quitResultRepository: QuitResultRepository): ViewModel() {
    val allQuitResult: LiveData<List<QuitResult>> = quitResultRepository.allQuitResults.asLiveData()
}