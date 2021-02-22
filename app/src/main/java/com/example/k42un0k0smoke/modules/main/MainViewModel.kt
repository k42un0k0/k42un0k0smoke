package com.example.k42un0k0smoke.modules.main

import androidx.lifecycle.*
import com.example.k42un0k0smoke.model.QuitResult
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.QuitResultRepository
import com.example.k42un0k0smoke.repository.StateRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val stateRepository: StateRepository,
    private val quitResultRepository: QuitResultRepository
) :
    ViewModel() {

    private val now: MutableLiveData<LocalDateTime> = MutableLiveData(LocalDateTime.now())
    val secondValue: LiveData<String> = now.map { MainViewModelUtil.secondsToString(duration(it)) }
    val minuteValue: LiveData<String> = now.map { MainViewModelUtil.minutesToString(duration(it)) }
    val hourValue: LiveData<String> = now.map { MainViewModelUtil.hoursToString(duration(it)) }
    val dayValue: LiveData<String> = now.map { MainViewModelUtil.daysToString(duration(it)) }
    val yearValue: LiveData<String> = now.map { MainViewModelUtil.yearsToString(duration(it)) }
    val isCounting: MutableLiveData<Boolean> = MutableLiveData(false)

    private fun duration(now: LocalDateTime): Duration {
        val diff = ChronoUnit.SECONDS.between(state.startAt ?: now, now)
        return Duration.ofSeconds(diff)
    }

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
        // TODO: totalSavingsをしっかり計算して吐き出す
        val quitResult = QuitResult(null, state.startAt!!, now.value!!, 1000)
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