package com.creativijaya.profilebook.domain.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDto(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val title: String = "",
    val picture: String = ""
) : Parcelable
