package com.example.k42un0k0smoke.implement.repository;

import android.content.SharedPreferences
import com.example.k42un0k0smoke.model.Setting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test


class SettingRepositoryImplTest {
    private lateinit var settingRepository: SettingRepositoryImpl
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        prefs = mockk(relaxed = true)
        prefsEditor = mockk(relaxed = true)
        every { prefs.edit() } returns prefsEditor
        settingRepository = SettingRepositoryImpl(prefs)
    }

    @Test
    fun save() {
        val setting = Setting(1000L)
        settingRepository.save(setting)
        verify(exactly = 1) {
            prefsEditor.putLong(any(), 1000L)
            prefsEditor.apply()
        }
    }

    @Test
    fun saveWhenStartAtIsNull() {
        val setting = Setting()
        settingRepository.save(setting)
        verify(exactly = 1) {
            prefsEditor.putLong(any(), 0)
            prefsEditor.apply()
        }
    }

    @Test
    fun find() {
        every { prefs.getLong(any(), any()) }
            .returns(1000L) // 2021-02-18T08:59:02

        val setting = settingRepository.find()
        assertThat(setting, `is`(Setting(1000L)))
    }
}