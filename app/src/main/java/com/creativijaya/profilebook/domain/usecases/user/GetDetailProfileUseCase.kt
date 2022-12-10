package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.domain.mapper.UserMapper
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.util.ext.mapTo

class GetDetailProfileUseCase(
    private val repository: UserRepository,
    private val mapper: UserMapper
) {
    suspend operator fun invoke(userId: String): ProfileDto {
        return repository.getProfileDetail(userId).mapTo {
            mapper.transformToDetailProfileDto(it)
        }
    }
}
