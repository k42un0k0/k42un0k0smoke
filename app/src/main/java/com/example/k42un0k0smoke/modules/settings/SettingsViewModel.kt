package com.example.k42un0k0smoke.modules.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k42un0k0smoke.model.Setting
import com.example.k42un0k0smoke.repository.SettingRepository
import com.example.k42un0k0smoke.repository.StateRepository
import javax.inject.Inject


class SettingsViewModel @Inject constructor(private val settingRepository: SettingRepository, private val stateRepository: StateRepository) :
    ViewModel() {
    val savingsPerDay: MutableLiveData<String> = MutableLiveData<String>()
    private var setting: Setting = settingRepository.find()


    init {
        savingsPerDay.value = setting.savingsPerDay.toString()
    }


    fun save() {
        savingsPerDay.value?.let {
            setting.savingsPerDay = it.toLong()
            settingRepository.save(setting)
        }
    }
}