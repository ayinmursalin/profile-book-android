package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse

interface PostRepository {
    suspend fun getPosts(page: Int, pageSize: Int = 20): BasePaginationResponse<PostResponse>
}
