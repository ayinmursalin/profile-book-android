package com.creativijaya.profilebook.domain.usecases.post

import com.creativijaya.profilebook.data.repository.PostRepository
import com.creativijaya.profilebook.domain.mapper.PostMapper
import com.creativijaya.profilebook.domain.mapper.UserMapper
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.util.ext.mapTo

class GetPostUseCase(
    private val repository: PostRepository,
    private val mapper: PostMapper,
    private val userMapper: UserMapper
) {
    suspend operator fun invoke(page: Int, pageSize: Int): BasePaginationDto<PostDto> {
        return repository.getPosts(page, pageSize).mapTo { response ->
            mapper.transformToPostListDto(response, userMapper::transformToDetailProfileDto)
        }
    }
}
