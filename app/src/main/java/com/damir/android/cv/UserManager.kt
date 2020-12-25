package com.damir.android.cv

import android.content.Context

object UserManager {
    lateinit var component: UserComponent
    fun init(context: Context) {
        component = UserComponent(context)
    }
}

class UserComponent(context: Context) {
    companion object {
        private const val PREFS_HAS_RESUME = "Prefs#hasResume"
        private const val PREFS_USER = "Prefs#User"
    }
    private val prefs = PrefManager(context)

    var hasResume: Boolean = false
        get() {
            return prefs.readBoolean(PREFS_HAS_RESUME)
        }
        set(value) {
            field = value
            prefs.writeBoolean(PREFS_HAS_RESUME, value)
        }

    var user: String? = null
        get() {
            return prefs.readString(PREFS_USER)
        }
        set(value) {
            field = value
            prefs.writeString(PREFS_USER, value)
        }
}