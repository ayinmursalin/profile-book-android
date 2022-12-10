package com.creativijaya.profilebook.domain.usecases.post

import com.creativijaya.profilebook.data.repository.PostRepository
import com.creativijaya.profilebook.domain.mapper.PostMapper
import com.creativijaya.profilebook.domain.models.post.PostDto

class AddFavoritePostUseCase(
    private val repository: PostRepository,
    private val mapper: PostMapper
) {
    suspend operator fun invoke(postDto: PostDto) {
        repository.addFavoritePost(mapper.transformToPostEntity(postDto))
    }
}
