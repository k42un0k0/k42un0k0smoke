package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.k42un0k0smoke.BuildConfig
import com.example.k42un0k0smoke.lib.SharedPreferencesWrapper
import com.example.k42un0k0smoke.model.State
import com.example.k42un0k0smoke.repository.StateRepository
import java.time.*
import javax.inject.Inject

class StateRepositoryImpl @Inject constructor(private val prefsWrapper: SharedPreferencesWrapper) :
    StateRepository {
    private val startAtKey = "state/startAt";
    private val ERROR_START_AT = -1L;

    override fun save(state: State) {
        val startAtOnSeconds = state.startAt?.toEpochSecond(ZoneOffset.UTC)

        prefsWrapper.putNullableLong(startAtKey, startAtOnSeconds).applyEditor()
    }

    override fun find(): State {
        val startAtOnSeconds = prefsWrapper.getNullableLong(startAtKey, ERROR_START_AT)
        val startAt = startAtOnSeconds?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
        return State(startAt)
    }
}