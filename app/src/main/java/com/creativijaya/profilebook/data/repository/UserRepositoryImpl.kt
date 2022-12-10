package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.network.services.UserService
import com.creativijaya.profilebook.util.ext.successOrError

class UserRepositoryImpl(
    private val service: UserService
) : UserRepository {

    override suspend fun getProfile(
        page: Int,
        pageSize: Int
    ) = successOrError {
        service.getProfile(page, pageSize)
    }

}
