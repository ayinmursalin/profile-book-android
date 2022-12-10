package com.creativijaya.profilebook.domain.usecases.post

import com.creativijaya.profilebook.data.repository.PostRepository

class RemoveFavoritePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String) {
        repository.removeFavoritePost(postId)
    }
}
