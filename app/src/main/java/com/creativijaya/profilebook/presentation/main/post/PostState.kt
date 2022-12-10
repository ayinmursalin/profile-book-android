package com.creativijaya.profilebook.presentation.main.post

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.post.PostDto

data class PostState(
    val postAsync: Async<BasePaginationDto<PostDto>> = Uninitialized,
    val favoritePostAsync: Async<List<PostDto>> = Uninitialized,
    val currentPage: Int = 1,
    val pageSize: Int = 20
) : MavericksState
