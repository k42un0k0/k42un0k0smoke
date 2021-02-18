package com.example.k42un0k0smoke.lib

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesWrapper @Inject constructor(val prefs: SharedPreferences) {
    val editor = prefs.edit()
    fun putNullableLong(key: String, value: Long?): SharedPreferencesWrapper {
        if (value == null) editor.remove(key)
        else editor.putLong(key, value)
        return this
    }

    fun getNullableLong(key: String, emptyValue: Long): Long? {
        val value = prefs.getLong(key, emptyValue)
        if(value==emptyValue)return null
        return value
    }

    fun applyEditor() {
        editor.apply()
    }
}