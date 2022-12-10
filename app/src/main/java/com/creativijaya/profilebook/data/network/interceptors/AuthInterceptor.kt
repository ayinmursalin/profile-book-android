package com.creativijaya.profilebook.data.network.interceptors

import com.creativijaya.profilebook.data.local.prefs.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val preferences: AppPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getToken()

        return chain.let {
            val builder = it.request().newBuilder()

            if (token != null) {
                builder.addHeader("Authorization", "Bearer $token")
            }

            it.proceed(builder.build())
        }
    }

}
