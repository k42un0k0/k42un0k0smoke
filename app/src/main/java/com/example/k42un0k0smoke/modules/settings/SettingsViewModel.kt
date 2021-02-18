package com.example.k42un0k0smoke.modules.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k42un0k0smoke.model.Setting
import com.example.k42un0k0smoke.repository.SettingRepository
import javax.inject.Inject


class SettingsViewModel @Inject constructor(private val settingRepository: SettingRepository) : ViewModel() {
    val costPerDay: MutableLiveData<Long> = MutableLiveData<Long>()
    private var setting: Setting = settingRepository.find() ?: Setting()

    init {
        costPerDay.value = setting.costPerDay
    }

    fun save(){
        setting.costPerDay = costPerDay.value ?: 100
        settingRepository.save(setting)
    }
}