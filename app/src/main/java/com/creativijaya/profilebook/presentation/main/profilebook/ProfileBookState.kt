package com.creativijaya.profilebook.presentation.main.profilebook

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto

data class ProfileBookState(
    val profileBookAsync: Async<BasePaginationDto<ProfileDto>> = Uninitialized,
    val currentPage: Int = 1,
    val pageSize: Int = 20
) : MavericksState
