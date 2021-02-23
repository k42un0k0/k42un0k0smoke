package com.example.k42un0k0smoke.modules.main

import androidx.lifecycle.*
import com.example.k42un0k0smoke.lib.DurationUtil
import com.example.k42un0k0smoke.model.QuitResult
import com.example.k42un0k0smoke.model.QuitResultTotalSavings
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.QuitResultRepository
import com.example.k42un0k0smoke.repository.SettingRepository
import com.example.k42un0k0smoke.repository.StateRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
    private val stateRepository: StateRepository,
    private val quitResultRepository: QuitResultRepository
) : ViewModel() {

    private val setting = settingRepository.find()
    private val now: MutableLiveData<LocalDateTime> = MutableLiveData(LocalDateTime.now())
    val totalSavingValue: LiveData<String> = now.map {
        QuitResultTotalSavings.compute(state.startAt ?: it, it, setting.savingsPerDay).toString()
    }

    private val duration: LiveData<Duration> =
        now.map { DurationUtil.fromLocalDateTime(state.startAt ?: it, it) }
    val secondValue: LiveData<String> = duration.map { MainViewModelUtil.secondsToString(it) }
    val minuteValue: LiveData<String> = duration.map { MainViewModelUtil.minutesToString(it) }
    val hourValue: LiveData<String> = duration.map { MainViewModelUtil.hoursToString(it) }
    val dayValue: LiveData<String> = duration.map { MainViewModelUtil.daysToString(it) }
    val yearValue: LiveData<String> = duration.map { MainViewModelUtil.yearsToString(it) }
    val isCounting: MutableLiveData<Boolean> = MutableLiveData(false)

    private var state: State = stateRepository.find()
    private var job: Job? = null

    init {
        if (state.startAt != null) {
            activateTimer()
        }
    }

    fun startTimer() {
        setNewState()
        activateTimer()
    }

    fun stopTimer() {
        insertResult()
        initializeValues()
        resetState()
        disposeTimer()
    }

    private fun setNewState() {
        state = State(LocalDateTime.now())
        stateRepository.save(state)
    }

    private fun resetState() {
        state = State(null)
        stateRepository.save(state)
    }

    private fun insertResult() {
        val startAt = state.startAt!!
        val endAt = now.value!!
        val totalSavings = QuitResultTotalSavings.compute(startAt, endAt, setting.savingsPerDay)
        val quitResult = QuitResult(
            null,
            startAt,
            endAt,
            totalSavings
        )
        viewModelScope.launch {
            quitResultRepository.insert(quitResult)
        }
    }

    private fun initializeValues() {
        now.value = state.startAt
    }

    private fun activateTimer() {
        isCounting.value = true
        job = viewModelScope.launch {
            while (true) {
                now.value = LocalDateTime.now()
                delay(1000)
            }
        }
    }

    private fun disposeTimer() {
        isCounting.value = false
        job?.cancel()
        job = null
    }
}

object MainViewModelUtil {
    fun secondsToString(duration: Duration): String {
        val seconds = (duration.seconds % 60)
        return "${seconds.toString().padStart(2, ' ')} 秒"
    }

    fun minutesToString(duration: Duration): String {
        val minutes = (duration.toMinutes() % 60)
        if (minutes == 0L) return ""
        return "${minutes.toString().padStart(2, ' ')} 分"
    }

    fun hoursToString(duration: Duration): String {
        val hours = (duration.toHours() % 24)
        if (hours == 0L) return ""
        return "${hours.toString().padStart(2, ' ')} 時"
    }

    fun daysToString(duration: Duration): String {
        val days = (duration.toDays() % 365)
        if (days == 0L) return ""
        return "${days.toString().padStart(2, ' ')} 日"
    }

    fun yearsToString(duration: Duration): String {
        val years = (duration.toDays() / 365)
        if (years == 0L) return ""
        return "${years.toString().padStart(2, ' ')} 年"
    }
}