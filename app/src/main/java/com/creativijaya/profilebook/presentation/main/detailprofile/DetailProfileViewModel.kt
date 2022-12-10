package com.creativijaya.profilebook.presentation.main.detailprofile

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.creativijaya.profilebook.domain.usecases.user.AddFriendUsecase
import com.creativijaya.profilebook.domain.usecases.user.CheckIsFriendUseCase
import com.creativijaya.profilebook.domain.usecases.user.GetDetailProfileUseCase
import com.creativijaya.profilebook.domain.usecases.user.RemoveFriendUsecase
import com.creativijaya.profilebook.presentation.base.BaseViewModel
import com.creativijaya.profilebook.util.ext.orFalse
import com.creativijaya.profilebook.util.ext.scopeInject

class DetailProfileViewModel(
    initialState: DetailProfileState,
    private val getDetailProfileUseCase: GetDetailProfileUseCase,
    private val addFriendUsecase: AddFriendUsecase,
    private val removeFriendUsecase: RemoveFriendUsecase,
    private val checkIsFriendUseCase: CheckIsFriendUseCase
) : BaseViewModel<DetailProfileState>(initialState) {

    init {
        checkIsFriend()
    }

    private fun checkIsFriend() {
        withState { state ->
            checkIsFriendUseCase(state.userId)
                .executeOnIo {
                    copy(isFriend = it)
                }
        }
    }

    fun getDetailProfile() {
        withState { state ->
            suspend {
                getDetailProfileUseCase(state.userId)
            }.executeOnIo {
                copy(profileDetailAsync = it)
            }
        }
    }

    fun toggleAddFriend() {
        withState { state ->
            if (state.isFriend.invoke().orFalse()) {
                removeFriend()
            } else {
                addFriend()
            }
        }
    }

    private fun addFriend() {
        withState { state ->
            state.profileDetailAsync.invoke()?.let {
                suspend {
                    addFriendUsecase(it)
                }.executeOnIo {
                    copy()
                }
            }
        }
    }

    private fun removeFriend() {
        withState { state ->
            suspend {
                removeFriendUsecase(state.userId)
            }.executeOnIo {
                copy()
            }
        }
    }

    companion object : MavericksViewModelFactory<DetailProfileViewModel, DetailProfileState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: DetailProfileState
        ): DetailProfileViewModel {
            val getDetailProfileUseCase: GetDetailProfileUseCase by viewModelContext.scopeInject()
            val addFriendUsecase: AddFriendUsecase by viewModelContext.scopeInject()
            val removeFriendUsecase: RemoveFriendUsecase by viewModelContext.scopeInject()
            val checkIsFriendUseCase: CheckIsFriendUseCase by viewModelContext.scopeInject()

            return DetailProfileViewModel(
                state,
                getDetailProfileUseCase,
                addFriendUsecase,
                removeFriendUsecase,
                checkIsFriendUseCase
            )
        }
    }
}
