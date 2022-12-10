package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.local.db.entities.post.PostEntity
import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(page: Int, pageSize: Int = 20): BasePaginationResponse<PostResponse>
    suspend fun addFavoritePost(postEntity: PostEntity)
    suspend fun removeFavoritePost(postId: String)
    fun getFavoritePost(): Flow<MutableList<PostEntity>>
}
