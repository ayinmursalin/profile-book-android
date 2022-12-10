package com.creativijaya.profilebook.domain.usecases.post

import com.creativijaya.profilebook.data.repository.PostRepository
import com.creativijaya.profilebook.domain.mapper.PostMapper
import com.creativijaya.profilebook.domain.models.post.PostDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoritePostUseCase(
    private val repository: PostRepository,
    private val mapper: PostMapper
) {
    operator fun invoke(): Flow<List<PostDto>> {
        return repository.getFavoritePost().map {
            mapper.transformToPostListDto(it)
        }
    }
}
