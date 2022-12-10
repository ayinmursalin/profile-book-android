package com.creativijaya.profilebook

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import com.creativijaya.profilebook.di.appModule
import com.creativijaya.profilebook.di.interactorModule
import com.creativijaya.profilebook.di.localDataSourceModule
import com.creativijaya.profilebook.di.mapperModule
import com.creativijaya.profilebook.di.remoteDataSourceModule
import com.creativijaya.profilebook.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeMavericks()
        initializeKoin()
    }

    private fun initializeMavericks() {
        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyApp)
            modules(
                appModule,
                localDataSourceModule,
                remoteDataSourceModule,
                repositoryModule,
                mapperModule,
                interactorModule
            )
        }
    }

}
