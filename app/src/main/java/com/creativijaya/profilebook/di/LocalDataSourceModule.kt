package com.creativijaya.profilebook.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.creativijaya.profilebook.MyAppConstant
import com.creativijaya.profilebook.data.local.db.AppDatabase
import com.creativijaya.profilebook.data.local.prefs.AppPreferences
import com.creativijaya.profilebook.data.local.prefs.AppPreferencesImpl
import org.koin.dsl.module

val localDataSourceModule = module {

    single {
        get<Application>().getSharedPreferences(MyAppConstant.APP_PREFERENCE, Context.MODE_PRIVATE)
    }

    single<AppPreferences> {
        AppPreferencesImpl(sharedPreferences = get())
    }

    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            MyAppConstant.APP_DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    single {
        get<AppDatabase>().getUser()
    }

}
