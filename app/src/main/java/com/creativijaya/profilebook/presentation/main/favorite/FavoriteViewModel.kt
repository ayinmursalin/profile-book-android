package com.creativijaya.profilebook.presentation.main.favorite

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.GetProfileBookUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class FavoriteViewModel(
    initialState: FavoriteState,
    private val getProfileBookUseCase: GetProfileBookUseCase
) : BaseViewModel<FavoriteState>(initialState) {


    companion object : MavericksViewModelFactory<FavoriteViewModel, FavoriteState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: FavoriteState
        ): FavoriteViewModel {
            val getProfileBookUseCase: GetProfileBookUseCase by viewModelContext.scopeInject()

            return FavoriteViewModel(state, getProfileBookUseCase)
        }
    }

}
