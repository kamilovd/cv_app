package com.damir.android.cv

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.appPrefs), Context.MODE_PRIVATE)

    fun writeString(key: String, value: String?) {
        with(pref.edit()) {
            putString(key, value).apply()
        }
    }

    fun writeBoolean(key: String, value: Boolean) {
        with(pref.edit()) {
            putBoolean(key, value).apply()
        }
    }

    fun readString(key: String): String? {
        return pref.getString(key, "")
    }

    fun readInteger(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun readBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }
}