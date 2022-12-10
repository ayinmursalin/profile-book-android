package com.creativijaya.profilebook.presentation.main.detailprofile

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.GetDetailProfileUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class DetailProfileViewModel(
    private val initialState: DetailProfileState,
    private val getDetailProfileUseCase: GetDetailProfileUseCase
) : BaseViewModel<DetailProfileState>(initialState) {

    fun getDetailProfile() {
        withState { state ->
            suspend {
                getDetailProfileUseCase(state.userId)
            }.executeOnIo {
                copy(profileDetailAsync = it)
            }
        }
    }

    companion object : MavericksViewModelFactory<DetailProfileViewModel, DetailProfileState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: DetailProfileState
        ): DetailProfileViewModel {
            val getDetailProfileUseCase: GetDetailProfileUseCase by viewModelContext.scopeInject()

            return DetailProfileViewModel(state, getDetailProfileUseCase)
        }
    }
}
