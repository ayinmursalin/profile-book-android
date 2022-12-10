package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.domain.mapper.PostMapper
import com.creativijaya.profilebook.domain.mapper.UserMapper
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.util.ext.mapTo

class GetUserPostUseCase(
    private val repository: UserRepository,
    private val mapper: UserMapper,
    private val postMapper: PostMapper
) {
    suspend operator fun invoke(userId: String): BasePaginationDto<PostDto> {
        return repository.getUserPosts(userId).mapTo { response ->
            postMapper.transformToPostListDto(response, mapper::transformToDetailProfileDto)
        }
    }
}
