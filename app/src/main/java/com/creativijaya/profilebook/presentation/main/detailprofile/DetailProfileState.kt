package com.creativijaya.profilebook.presentation.main.detailprofile

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.creativijaya.profilebook.domain.models.user.ProfileDto

data class DetailProfileState(
    val profileDetailAsync: Async<ProfileDto> = Uninitialized,
    val isFriend: Async<Boolean> = Uninitialized,
    val userId: String
) : MavericksState {
    constructor(args: DetailProfileArgs) : this(
        userId = args.userId
    )
}
