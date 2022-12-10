package com.creativijaya.profilebook.presentation.main.favorite

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto

data class FavoriteState(
    val favoritePostAsync: Async<List<PostDto>> = Uninitialized
) : MavericksState
