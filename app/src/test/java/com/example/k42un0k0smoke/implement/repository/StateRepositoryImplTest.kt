package com.example.k42un0k0smoke.implement.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.k42un0k0smoke.model.State
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore
import java.time.LocalDateTime
import java.time.ZoneOffset


class StateRepositoryImplTest {
    private lateinit var stateRepositoryImpl: StateRepositoryImpl
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor
    private var mockEpockSeconds: Long = 1613638742
    private var mockLocalDateTime = LocalDateTime.ofEpochSecond(mockEpockSeconds, 0, ZoneOffset.UTC)

    @Before
    fun setUp() {
        val sharedPrefs = mockk<SharedPreferences>(relaxed = true)
        sharedPrefsEditor =
            mockk<SharedPreferences.Editor>(relaxed = true)
        every { sharedPrefs.edit() }
            .returns(sharedPrefsEditor)
        every { sharedPrefsEditor.putLong(any(), any()) }
            .returns(sharedPrefsEditor)
        every { sharedPrefs.getLong(any(), any()) }
            .returns(mockEpockSeconds) // 2021-02-18T08:59:02
        stateRepositoryImpl = StateRepositoryImpl(sharedPrefs)
    }

    @Test
    fun save() {
        val state = State(mockLocalDateTime)
        stateRepositoryImpl.save(state)
        verify{
            sharedPrefsEditor.putLong(any(), mockEpockSeconds)
        }
    }

    @Test
    @Ignore("don't implement test yet")
    fun saveWhenStartAtIsNull() {
    }

    @Test
    fun find() {
        val state = stateRepositoryImpl.find()
        val expect = LocalDateTime.of(2021, 2, 18, 8, 59, 2, 0)
        assertEquals(expect, state.startAt)
    }

    @Test
    @Ignore("don't implement test yet")
    fun findWhenSharedPreferencesIsEmpty() {
    }
}