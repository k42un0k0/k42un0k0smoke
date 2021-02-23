package com.example.k42un0k0smoke.implement.repository

import android.content.SharedPreferences
import com.example.k42un0k0smoke.extention.shared_preferences.getNullableLong
import com.example.k42un0k0smoke.extention.shared_preferences.putNullableLong
import com.example.k42un0k0smoke.model.State
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.ZoneOffset


class StateRepositoryImplTest {
    private lateinit var stateRepositoryImpl: StateRepositoryImpl
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    private var mockEpockSeconds: Long = 1613638742
    private var mockLocalDateTime = LocalDateTime.ofEpochSecond(mockEpockSeconds, 0, ZoneOffset.UTC)

    @Before
    fun setUp() {
        prefs = mockk(relaxed = true)
        prefsEditor = mockk(relaxed = true)
        every { prefs.edit() } returns prefsEditor
        every { prefsEditor.putNullableLong(any(), any()) } returns prefsEditor
        stateRepositoryImpl = StateRepositoryImpl(prefs)
    }

    @Test
    fun save() {
        val state = State(mockLocalDateTime)
        stateRepositoryImpl.save(state)
        verify(exactly = 1) {
            prefs.edit()
            prefsEditor.putNullableLong(any(), mockEpockSeconds)
            prefsEditor.apply()
        }
    }

    @Test
    fun saveWhenStartAtIsNull() {
        val state = State(null)
        stateRepositoryImpl.save(state)
        verify(exactly = 1) {
            prefs.edit()
            prefsEditor.putNullableLong(any(), null)
            prefsEditor.apply()
        }
    }

    @Test
    fun find() {
        every {
            prefs.getNullableLong(
                any(),
                any()
            )
        } returns mockEpockSeconds // 2021-02-18T08:59:02

        val state = stateRepositoryImpl.find()
        val expect = LocalDateTime.of(2021, 2, 18, 8, 59, 2, 0)
        assertThat(state, `is`(State(expect)))
    }

    @Test
    fun findWhenSharedPreferencesIsEmpty() {
        // FIXME: getNullableLongのモックをするとnpeが出る
        every { prefs.getLong(any(), any()) } returns -1

        val state = stateRepositoryImpl.find()
        assertThat(state, `is`(State(null)))
    }
}