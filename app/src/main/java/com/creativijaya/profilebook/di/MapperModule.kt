package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.domain.mapper.UserMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        UserMapper()
    }

}
