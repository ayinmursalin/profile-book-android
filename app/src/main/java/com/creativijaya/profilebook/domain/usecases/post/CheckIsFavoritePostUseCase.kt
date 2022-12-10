package com.creativijaya.profilebook.domain.usecases.post

import com.creativijaya.profilebook.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckIsFavoritePostUseCase(
    private val repository: PostRepository
) {
    operator fun invoke(postId: String): Flow<Boolean> {
        return repository.checkIsFavorite(postId).map {
            it.isNotEmpty()
        }
    }
}
