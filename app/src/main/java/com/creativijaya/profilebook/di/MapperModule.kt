package com.creativijaya.profilebook.di

import com.creativijaya.profilebook.domain.mapper.MainMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        MainMapper()
    }

}
