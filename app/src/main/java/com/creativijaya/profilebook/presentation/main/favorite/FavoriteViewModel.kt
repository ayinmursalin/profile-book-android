package com.creativijaya.profilebook.presentation.main.favorite

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.post.GetFavoritePostUseCase
import com.creativijaya.profilebook.domain.usecases.post.RemoveFavoritePostUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class FavoriteViewModel(
    initialState: FavoriteState,
    private val getFavoritePostUseCase: GetFavoritePostUseCase,
    private val removeFavoritePostUseCase: RemoveFavoritePostUseCase
) : BaseViewModel<FavoriteState>(initialState) {

    fun getFavoritePosts() {
        getFavoritePostUseCase()
            .executeOnIo {
                copy(favoritePostAsync = it)
            }
    }

    fun removeFavoritePost(postId: String) {
        suspend {
            removeFavoritePostUseCase(postId)
        }.executeOnIo {
            copy()
        }
    }

    companion object : MavericksViewModelFactory<FavoriteViewModel, FavoriteState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: FavoriteState
        ): FavoriteViewModel {
            val getFavoritePostUseCase: GetFavoritePostUseCase by viewModelContext.scopeInject()
            val removeFavoritePostUseCase: RemoveFavoritePostUseCase by viewModelContext.scopeInject()

            return FavoriteViewModel(state, getFavoritePostUseCase, removeFavoritePostUseCase)
        }
    }

}
