package com.creativijaya.profilebook.presentation.main.post

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.GetProfileBookUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class PostViewModel(
    initialState: PostState,
    private val getProfileBookUseCase: GetProfileBookUseCase
) : BaseViewModel<PostState>(initialState) {


    companion object : MavericksViewModelFactory<PostViewModel, PostState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: PostState
        ): PostViewModel {
            val getProfileBookUseCase: GetProfileBookUseCase by viewModelContext.scopeInject()

            return PostViewModel(state, getProfileBookUseCase)
        }
    }

}
