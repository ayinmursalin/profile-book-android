package com.creativijaya.profilebook.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.creativijaya.profilebook.BuildConfig
import com.creativijaya.profilebook.data.network.interceptors.AuthInterceptor
import com.creativijaya.profilebook.data.network.services.PostService
import com.creativijaya.profilebook.data.network.services.UserService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {

    single {
        Cache(get<Application>().cacheDir, 10 * 1024 * 1024L)
    }

    single {
        val builder = OkHttpClient.Builder()
            .cache(get())
            .followSslRedirects(true)
            .addInterceptor(AuthInterceptor(appId = "6393ed1d65944d064b5243bd"))

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                ChuckerInterceptor.Builder(androidContext())
                    .alwaysReadResponseBody(true)
                    .build()
            )
        }

        builder.build()
    }

    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<UserService> {
        get<Retrofit>().create(UserService::class.java)
    }

    single<PostService> {
        get<Retrofit>().create(PostService::class.java)
    }

}
