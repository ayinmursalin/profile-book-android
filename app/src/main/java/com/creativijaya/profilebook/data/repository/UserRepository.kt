package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.main.MainProfileResponse

interface UserRepository {
    suspend fun getProfile(page: Int, pageSize: Int = 20): BasePaginationResponse<MainProfileResponse>
}
