package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.network.services.PostService
import com.creativijaya.profilebook.util.ext.successOrError

class PostRepositoryImpl(
    private val service: PostService
) : PostRepository {

    override suspend fun getPosts(page: Int, pageSize: Int) = successOrError {
        service.getPosts(page, pageSize)
    }

}
