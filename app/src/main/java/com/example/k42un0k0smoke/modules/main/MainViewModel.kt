package com.example.k42un0k0smoke.modules.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.StateRepository
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

class MainViewModel @Inject constructor(private val stateRepository: StateRepository) :
    ViewModel() {

    val secondValue: MutableLiveData<String> = MutableLiveData<String>()
    val minuteValue: MutableLiveData<String> = MutableLiveData<String>()
    val hourValue: MutableLiveData<String> = MutableLiveData<String>()
    val dayValue: MutableLiveData<String> = MutableLiveData<String>()
    val yearValue: MutableLiveData<String> = MutableLiveData<String>()
    val isCounting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private var timer: Timer? = null
    private val timerTask: TimerTask.() -> Unit = {
        viewModelScope.launch {
            setTimeMembers(state.startAt!!)
        }
    }

    private var state: State = stateRepository.find()

    init {
        resetTimeMembers()
        if (state.startAt != null) {
            setTimer()
        }
    }

    fun startTimer() {
        setNowToState()
        setTimer()
    }

    fun stopTimer() {
        resetTimeMembers()
        resetTimer()
    }

    private fun setNowToState() {
        state.startAt = LocalDateTime.now()
        stateRepository.save(state)
    }

    private fun setTimer() {
        isCounting.value = true
        timer = Timer()
        timer?.scheduleAtFixedRate(0, 1000, timerTask)
    }

    private fun resetTimer() {
        isCounting.value = false
        timer?.cancel()
        timer = null
    }

    private fun setTimeMembers(startAt: LocalDateTime) {
        val now = LocalDateTime.now()
        val diff = ChronoUnit.SECONDS.between(startAt, now)
        val duration = Duration.ofSeconds(diff)
        secondValue.value = MainViewModelUtil.secondsToString(duration)
        minuteValue.value = MainViewModelUtil.minutesToString(duration)
        hourValue.value = MainViewModelUtil.hoursToString(duration)
        dayValue.value = MainViewModelUtil.daysToString(duration)
        yearValue.value = MainViewModelUtil.yearsToString(duration)
    }

    private fun resetTimeMembers() {
        secondValue.value = "0 秒"
        minuteValue.value = ""
        hourValue.value = ""
        dayValue.value = ""
        yearValue.value = ""
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