package com.creativijaya.profilebook.presentation.main.profilebook

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.main.MainProfileDto

data class ProfileBookState(
    val profileBookAsync: Async<BasePaginationDto<MainProfileDto>> = Uninitialized
) : MavericksState
