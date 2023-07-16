package com.example.chucknorris.data

import android.content.SharedPreferences

class UserPreferences(private val preferences: SharedPreferences) {
    companion object {
        const val USER_NAME = "USER_NAME"
         }

    fun saveName(name: String) {
        preferences.edit().putString(USER_NAME, name).apply()
    }

    fun getName(): String? {
        return preferences.getString(USER_NAME,null)
    }
}