package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.domain.usecases.GetProfileBookUseCase
import org.koin.dsl.module

val interactorModule = module {

    single {
        GetProfileBookUseCase(get(), get())
    }

}