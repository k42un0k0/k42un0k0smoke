package com.example.k42un0k0smoke.extention.shared_preferences

import android.content.SharedPreferences

fun SharedPreferences.Editor.putNullableLong(
    key: String,
    value: Long?
): SharedPreferences.Editor {
    if (value == null) remove(key)
    else putLong(key, value)
    return this
}