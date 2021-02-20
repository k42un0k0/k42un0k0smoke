package com.example.k42un0k0smoke.modules.settings

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k42un0k0smoke.model.Setting
import com.example.k42un0k0smoke.repository.SettingRepository
import javax.inject.Inject


class SettingsViewModel @Inject constructor(private val settingRepository: SettingRepository) :
    ViewModel() {
    val costPerDay: MutableLiveData<String> = MutableLiveData<String>()
    private var setting: Setting = settingRepository.find() ?: Setting()

    init {
        costPerDay.value = setting.costPerDay.toString()
    }


    fun save() {
        costPerDay.value?.let {
            setting.costPerDay = it.toLong()
            settingRepository.save(setting)
        }
    }
}