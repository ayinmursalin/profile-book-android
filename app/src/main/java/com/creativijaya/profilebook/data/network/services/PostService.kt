package com.creativijaya.profilebook.data.network.services

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("post")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int = 20
    ): BasePaginationResponse<PostResponse>

}
