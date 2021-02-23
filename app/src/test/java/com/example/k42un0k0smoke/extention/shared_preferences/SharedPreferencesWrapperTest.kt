package com.example.k42un0k0smoke.extention.shared_preferences

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class SharedPreferencesWrapperTest {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    private var mockEpochSeconds: Long = 1613638742
    private var mockLocalDateTime = LocalDateTime.ofEpochSecond(mockEpochSeconds, 0, ZoneOffset.UTC)

    @Before
    fun setUp() {
        prefs = mockk(relaxed = true)
        prefsEditor = mockk(relaxed = true)
        every { prefs.edit() } returns prefsEditor
    }

    @Test
    fun putNullableLong() {
        val expectKey = "testPutNullableLong"
        val expectValue = 10L
        prefs.edit().putNullableLong(expectKey, expectValue)

        verify(exactly = 1){ prefsEditor.putLong(expectKey,expectValue) }
    }

    @Test
    fun putNullableLongWithNull() {
        val expectKey = "testPutNullableLong"
        prefs.edit().putNullableLong(expectKey, null)

        verify(exactly = 1){ prefsEditor.remove(expectKey) }
    }

    @Test
    fun getNullableLong() {
        val expect = 10L
        every { prefs.getLong(any(),any()) } returns expect
        val actual = prefs.getNullableLong("testPutNullableLong", -1)

        assertEquals(expect, actual)
    }

    @Test
    fun getNullableLongWithNull() {
        val emptyValue = -1L
        every { prefs.getLong(any(),any()) } returns emptyValue
        val actual = prefs.getNullableLong("testPutNullableLong", emptyValue)

        assertEquals(null, actual)
    }
}