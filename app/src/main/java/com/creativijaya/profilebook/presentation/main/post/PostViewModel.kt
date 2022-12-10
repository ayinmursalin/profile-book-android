package com.creativijaya.profilebook.presentation.main.post

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.domain.usecases.post.AddFavoritePostUseCase
import com.creativijaya.profilebook.domain.usecases.post.GetFavoritePostUseCase
import com.creativijaya.profilebook.domain.usecases.post.GetPostUseCase
import com.creativijaya.profilebook.domain.usecases.post.RemoveFavoritePostUseCase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.scopeInject

class PostViewModel(
    initialState: PostState,
    private val getPostUseCase: GetPostUseCase,
    private val addFavoritePostUseCase: AddFavoritePostUseCase,
    private val getFavoritePostUseCase: GetFavoritePostUseCase,
    private val removeFavoritePostUseCase: RemoveFavoritePostUseCase
) : BaseViewModel<PostState>(initialState) {

    init {
        getFavoritePost()
    }

    private fun getFavoritePost() {
        getFavoritePostUseCase()
            .executeOnIo {
                copy(favoritePostAsync = it)
            }
    }

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

    fun removeFavoritePost(postId: String) {
        suspend {
            removeFavoritePostUseCase(postId)
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
            val getFavoritePostUseCase: GetFavoritePostUseCase by viewModelContext.scopeInject()
            val removeFavoritePostUseCase: RemoveFavoritePostUseCase by viewModelContext.scopeInject()

            return PostViewModel(
                state,
                getPostUseCase,
                addFavoritePostUseCase,
                getFavoritePostUseCase,
                removeFavoritePostUseCase
            )
        }
    }

}
