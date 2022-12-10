package com.creativijaya.profilebook.data.local.prefs

interface AppPreferences {
    fun setToken(token: String?)
    fun getToken(): String?
}
