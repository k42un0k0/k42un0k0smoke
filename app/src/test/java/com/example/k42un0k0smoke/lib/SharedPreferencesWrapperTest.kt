package com.example.k42un0k0smoke.lib

import android.content.SharedPreferences
import com.example.k42un0k0smoke.implement.repository.StateRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class SharedPreferencesWrapperTest {
    private lateinit var prefsWrapper: SharedPreferencesWrapper
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    private var mockEpockSeconds: Long = 1613638742
    private var mockLocalDateTime = LocalDateTime.ofEpochSecond(mockEpockSeconds, 0, ZoneOffset.UTC)

    @Before
    fun setUp() {
        prefs = mockk(relaxed = true)
        prefsEditor = mockk(relaxed = true)
        every { prefs.edit() } returns prefsEditor
        prefsWrapper = SharedPreferencesWrapper(prefs)
    }

    @Test
    fun putNullableLong() {
        val expectKey = "testPutNullableLong"
        val expectValue = 10L
        prefsWrapper.putNullableLong(expectKey, expectValue)

        verify(exactly = 1){ prefsEditor.putLong(expectKey,expectValue) }
    }

    @Test
    fun putNullableLongWithNull() {
        val expectKey = "testPutNullableLong"
        prefsWrapper.putNullableLong(expectKey, null)

        verify(exactly = 1){ prefsEditor.remove(expectKey) }
    }

    @Test
    fun getNullableLong() {
        val expect = 10L
        every { prefs.getLong(any(),any()) } returns expect
        val actual = prefsWrapper.getNullableLong("testPutNullableLong", -1)

        assertEquals(expect, actual)
    }

    @Test
    fun getNullableLongWithNull() {
        val emptyValue = -1L
        every { prefs.getLong(any(),any()) } returns emptyValue
        val actual = prefsWrapper.getNullableLong("testPutNullableLong", emptyValue)

        assertEquals(null, actual)
    }

    @Test
    fun applyEditor() {
        prefsWrapper.applyEditor()

        verify(exactly = 1){ prefsEditor.apply() }
    }
}