package com.creativijaya.profilebook.presentation.main.profilebook

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.user.GetProfileBookUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class ProfileBookViewModel(
    initialState: ProfileBookState,
    private val getProfileBookUseCase: GetProfileBookUseCase
) : BaseViewModel<ProfileBookState>(initialState) {

    fun getProfileBook(page: Int = 1) {
        withState { state ->
            suspend {
                getProfileBookUseCase(state.currentPage, state.pageSize)
            }.executeOnIo {
                copy(
                    profileBookAsync = it,
                    currentPage = page
                )
            }
        }
    }

    companion object : MavericksViewModelFactory<ProfileBookViewModel, ProfileBookState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ProfileBookState
        ): ProfileBookViewModel {
            val getProfileBookUseCase: GetProfileBookUseCase by viewModelContext.scopeInject()

            return ProfileBookViewModel(state, getProfileBookUseCase)
        }
    }

}
