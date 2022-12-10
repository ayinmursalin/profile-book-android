package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.local.db.daos.PostDao
import com.creativijaya.profilebook.data.local.db.entities.post.PostEntity
import com.creativijaya.profilebook.data.network.services.PostService
import com.creativijaya.profilebook.util.ext.successOrError
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val service: PostService,
    private val dao: PostDao
) : PostRepository {

    override suspend fun getPosts(page: Int, pageSize: Int) = successOrError {
        service.getPosts(page, pageSize)
    }

    override suspend fun addFavoritePost(postEntity: PostEntity) {
        dao.addFavoritePost(postEntity)
    }

    override suspend fun removeFavoritePost(postId: String) {
        dao.removeFavoritePost(postId)
    }

    override fun getFavoritePost(): Flow<MutableList<PostEntity>> {
        return dao.getFavoritePost()
    }
}
