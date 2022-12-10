package com.creativijaya.profilebook.domain.models.post

import android.os.Parcelable
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDto(
    val id: String = "",
    val owner: ProfileDto = ProfileDto(),
    val image: String = "",
    val publishDate: String = "",
    val text: String = "",
    val likes: Int = 0,
    val tags: List<String> = emptyList()
) : Parcelable
