package com.example.k42un0k0smoke.repository

import com.example.k42un0k0smoke.model.Setting

interface SettingRepository {
    fun save(setting: Setting);
    fun find(): Setting;
}