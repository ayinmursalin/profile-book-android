package com.creativijaya.profilebook.presentation.main.favorite

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.post.GetFavoritePostUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class FavoriteViewModel(
    initialState: FavoriteState,
    private val getFavoritePostUseCase: GetFavoritePostUseCase
) : BaseViewModel<FavoriteState>(initialState) {

    fun getFavoritePosts() {
        getFavoritePostUseCase()
            .executeOnIo {
                copy(favoritePostAsync = it)
            }
    }

    companion object : MavericksViewModelFactory<FavoriteViewModel, FavoriteState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: FavoriteState
        ): FavoriteViewModel {
            val getFavoritePostUseCase: GetFavoritePostUseCase by viewModelContext.scopeInject()

            return FavoriteViewModel(state, getFavoritePostUseCase)
        }
    }

}
