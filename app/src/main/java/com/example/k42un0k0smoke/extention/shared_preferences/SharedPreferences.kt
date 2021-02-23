package com.example.k42un0k0smoke.extention.shared_preferences

import android.content.SharedPreferences

fun SharedPreferences.getNullableLong(key: String, emptyValue: Long): Long? {
    val value = getLong(key, emptyValue)
    if (value == emptyValue) return null
    return value
}