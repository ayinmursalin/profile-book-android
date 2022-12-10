package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.data.repository.PostRepository
import com.creativijaya.profilebook.data.repository.PostRepositoryImpl
import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

}
