package com.creativijaya.profilebook.data.local.prefs

import android.content.SharedPreferences

class AppPreferencesImpl constructor(
    private val sharedPreferences: SharedPreferences
) : AppPreferences {

    override fun setToken(token: String?) {
        sharedPreferences.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    companion object {
        private const val KEY_TOKEN = ".key-token"
    }
}
