package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.k42un0k0smoke.BuildConfig
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.StateRepository
import java.time.*
import javax.inject.Inject

class StateRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    StateRepository {
    private val startAtKey = "state/startAt";
    private val ERROR_START_AT = -1L;

    override fun save(state: State) {
        val editor = sharedPreferences.edit()
        val startAtOnSeconds = state.startAt?.toEpochSecond(ZoneOffset.UTC)

        if (startAtOnSeconds != null)
            editor.putLong(startAtKey, startAtOnSeconds).apply()
    }

    override fun find(): State {
        var startAt: LocalDateTime? = null
        val startAtOnSeconds = sharedPreferences.getLong(
            startAtKey, ERROR_START_AT
        )
        if (startAtOnSeconds != ERROR_START_AT) startAt = LocalDateTime.ofEpochSecond(startAtOnSeconds, 0, ZoneOffset.UTC)
        return State(startAt)
    }
}