package com.creativijaya.profilebook.presentation.main.post

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.domain.usecases.post.AddFavoritePostUseCase
import com.creativijaya.profilebook.domain.usecases.post.GetPostUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class PostViewModel(
    initialState: PostState,
    private val getPostUseCase: GetPostUseCase,
    private val addFavoritePostUseCase: AddFavoritePostUseCase
) : BaseViewModel<PostState>(initialState) {

    fun getPost(page: Int = 1) {
        withState { state ->
            suspend {
                getPostUseCase(page, state.pageSize)
            }.executeOnIo {
                copy(
                    postAsync = it,
                    currentPage = page
                )
            }
        }
    }

    fun addFavoritePost(postDto: PostDto) {
        suspend {
            addFavoritePostUseCase(postDto)
        }.executeOnIo {
            copy()
        }
    }

    companion object : MavericksViewModelFactory<PostViewModel, PostState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: PostState
        ): PostViewModel {
            val getPostUseCase: GetPostUseCase by viewModelContext.scopeInject()
            val addFavoritePostUseCase: AddFavoritePostUseCase by viewModelContext.scopeInject()

            return PostViewModel(state, getPostUseCase, addFavoritePostUseCase)
        }
    }

}
