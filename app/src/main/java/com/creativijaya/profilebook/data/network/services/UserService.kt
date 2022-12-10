package com.creativijaya.profilebook.data.network.services

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.main.MainProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getProfile(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int = 20
    ): BasePaginationResponse<MainProfileResponse>

}
