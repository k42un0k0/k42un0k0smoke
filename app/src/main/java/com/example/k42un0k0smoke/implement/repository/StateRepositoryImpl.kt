package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import com.example.k42un0k0smoke.extention.shared_preferences.getNullableLong
import com.example.k42un0k0smoke.extention.shared_preferences.putNullableLong
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.StateRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class StateRepositoryImpl @Inject constructor(private val prefs: SharedPreferences) :
    StateRepository {
    private val startAtKey = "state/startAt";
    private val ERROR_START_AT = -1L;

    override fun save(state: State) {
        val startAtOnSeconds = state.startAt?.toEpochSecond(ZoneOffset.UTC)

        prefs.edit().putNullableLong(startAtKey, startAtOnSeconds).apply()
    }

    override fun find(): State {
        val startAtOnSeconds = prefs.getNullableLong(startAtKey, ERROR_START_AT)
        val startAt = startAtOnSeconds?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
        return State(startAt)
    }
}