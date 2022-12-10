package com.creativijaya.profilebook.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val appId: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.let {
            val builder = it.request().newBuilder()

            builder.addHeader("app-id", appId)

            it.proceed(builder.build())
        }
    }

}
