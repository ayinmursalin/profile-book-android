package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.domain.mapper.UserMapper
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.util.ext.mapTo

class GetProfileBookUseCase(
    private val repository: UserRepository,
    private val mapper: UserMapper
) {
    suspend operator fun invoke(page: Int, pageSize: Int): BasePaginationDto<ProfileDto> {
        return repository.getProfile(page, pageSize).mapTo {
            mapper.transformToProfileBookListDto(it)
        }
    }
}
