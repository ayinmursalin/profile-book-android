package com.creativijaya.profilebook.presentation.main.post

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.post.GetPostUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetProfileBookUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class PostViewModel(
    initialState: PostState,
    private val getPostUseCase: GetPostUseCase
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

    companion object : MavericksViewModelFactory<PostViewModel, PostState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: PostState
        ): PostViewModel {
            val getPostUseCase: GetPostUseCase by viewModelContext.scopeInject()

            return PostViewModel(state, getPostUseCase)
        }
    }

}
