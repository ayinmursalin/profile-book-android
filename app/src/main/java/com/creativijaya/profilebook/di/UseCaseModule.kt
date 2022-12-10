package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.domain.usecases.post.GetPostUseCase
import com.creativijaya.profilebook.domain.usecases.user.AddFriendUsecase
import com.creativijaya.profilebook.domain.usecases.user.CheckIsFriendUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetDetailProfileUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetProfileBookUseCase
import com.creativijaya.profilebook.domain.usecases.user.RemoveFriendUsecase
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

    single {
        CheckIsFriendUseCase(get())
    }

    single {
        AddFriendUsecase(get(), get())
    }

    single {
        RemoveFriendUsecase(get())
    }

}
