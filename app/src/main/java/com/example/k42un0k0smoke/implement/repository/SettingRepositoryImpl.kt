package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import com.example.k42un0k0smoke.model.Setting
import com.example.k42un0k0smoke.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(val sharedPreferences: SharedPreferences) :
    SettingRepository {
    private final val kCostPerDay = "setting/costPerDay";
    override fun save(setting: Setting) {
        val editor = sharedPreferences.edit()
        editor.putLong(kCostPerDay, setting.costPerDay)
        editor.apply()
    }

    override fun find(): Setting {
        return Setting(sharedPreferences.getLong(kCostPerDay,0))
    }
}