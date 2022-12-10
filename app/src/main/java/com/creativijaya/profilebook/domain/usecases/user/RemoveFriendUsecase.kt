package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository

class RemoveFriendUsecase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(profileId: String) {
        repository.removeFriend(profileId)
    }
}
