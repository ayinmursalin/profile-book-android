package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.domain.usecases.post.GetPostUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetDetailProfileUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetProfileBookUseCase
import org.koin.dsl.module

val interactorModule = module {

    single {
        GetProfileBookUseCase(get(), get())
    }

    single {
        GetDetailProfileUseCase(get(), get())
    }

    single {
        GetPostUseCase(get(), get(), get())
    }

}
