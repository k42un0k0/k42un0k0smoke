package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import com.example.k42un0k0smoke.lib.SharedPreferencesWrapper
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
    private lateinit var prefsWrapper: SharedPreferencesWrapper
    private var mockEpockSeconds: Long = 1613638742
    private var mockLocalDateTime = LocalDateTime.ofEpochSecond(mockEpockSeconds, 0, ZoneOffset.UTC)

    @Before
    fun setUp() {
        prefsWrapper = mockk(relaxed = true)
        every { prefsWrapper.putNullableLong(any(), any()) } returns prefsWrapper
        stateRepositoryImpl = StateRepositoryImpl(prefsWrapper)
    }

    @Test
    fun save() {
        val state = State(mockLocalDateTime)
        stateRepositoryImpl.save(state)
        verify(exactly = 1) {
            prefsWrapper.putNullableLong(any(), mockEpockSeconds)
        }
        verify(exactly = 1) {
            prefsWrapper.applyEditor()
        }
    }

    @Test
    fun saveWhenStartAtIsNull() {
        val state = State(null)
        stateRepositoryImpl.save(state)
        verify(exactly = 1) {
            prefsWrapper.putNullableLong(any(), null)
        }
    }

    @Test
    fun find() {
        every { prefsWrapper.getNullableLong(any(), any()) }
            .returns(mockEpockSeconds) // 2021-02-18T08:59:02

        val state = stateRepositoryImpl.find()
        val expect = LocalDateTime.of(2021, 2, 18, 8, 59, 2, 0)
        assertEquals(expect, state.startAt)
    }

    @Test
    fun findWhenSharedPreferencesIsEmpty() {
        every { prefsWrapper.getNullableLong(any(), any()) }
            .returns(null) // 2021-02-18T08:59:02

        val state = stateRepositoryImpl.find()
        val expect = null
        assertEquals(expect, state.startAt)
    }
}