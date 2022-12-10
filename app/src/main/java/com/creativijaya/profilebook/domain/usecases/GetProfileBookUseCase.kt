package com.creativijaya.profilebook.domain.usecases

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.domain.mapper.MainMapper
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.main.MainProfileDto
import com.creativijaya.profilebook.util.ext.mapTo

class GetProfileBookUseCase(
    private val repository: UserRepository,
    private val mapper: MainMapper
) {
    suspend operator fun invoke(page: Int, pageSize: Int): BasePaginationDto<MainProfileDto> {
        return repository.getProfile(page, pageSize).mapTo {
            mapper.transformToProfileBookListDto(it)
        }
    }
}
