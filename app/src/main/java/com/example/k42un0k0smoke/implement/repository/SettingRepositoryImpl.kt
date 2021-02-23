package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import com.example.k42un0k0smoke.model.Setting
import com.example.k42un0k0smoke.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(val prefs: SharedPreferences) :
    SettingRepository {
    private val savingsPerDayKey = "setting/savingsPerDay";
    override fun save(setting: Setting) {
        val editor = prefs.edit()
        editor.putLong(savingsPerDayKey, setting.savingsPerDay).apply()
    }

    override fun find(): Setting {
        return Setting(prefs.getLong(savingsPerDayKey,0))
    }
}