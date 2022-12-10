package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.domain.mapper.UserMapper
import com.creativijaya.profilebook.domain.models.user.ProfileDto

class AddFriendUsecase(
    private val repository: UserRepository,
    private val mapper: UserMapper
) {
    suspend operator fun invoke(profileDto: ProfileDto) {
        repository.addFriend(mapper.transformToUserEntity(profileDto))
    }
}
