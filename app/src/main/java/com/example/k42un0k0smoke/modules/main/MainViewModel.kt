package com.example.k42un0k0smoke.modules.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.StateRepository
import java.time.LocalDateTime
import javax.inject.Inject

class MainViewModel @Inject constructor(private val stateRepository: StateRepository) : ViewModel() {
    val startAt: MutableLiveData<LocalDateTime> = MutableLiveData<LocalDateTime>()
    private var state: State = stateRepository.find()

    init {
        startAt.value = state.startAt
    }

    fun save(){
        state.startAt = startAt.value ?: LocalDateTime.now()
        stateRepository.save(state)
    }
}