package com.creativijaya.profilebook.data.network.services

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import com.creativijaya.profilebook.data.network.responses.user.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getProfile(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int = 20
    ): BasePaginationResponse<ProfileResponse>

    @GET("user/{user_id}")
    suspend fun getProfileDetail(
        @Path("user_id") userId: String
    ): ProfileResponse

    @GET("user/{user_id}/post")
    suspend fun getUserPosts(
        @Path("user_id") userId: String
    ): BasePaginationResponse<PostResponse>

}
