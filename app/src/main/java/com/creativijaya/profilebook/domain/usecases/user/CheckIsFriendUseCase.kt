package com.creativijaya.profilebook.domain.usecases.user

import com.creativijaya.profilebook.data.repository.UserRepository
import com.creativijaya.profilebook.util.ext.mapTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckIsFriendUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(profileId: String): Flow<Boolean> {
        return repository.checkIsFriend(profileId).map {
            it.isNotEmpty()
        }
    }
}
