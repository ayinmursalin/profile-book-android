package com.creativijaya.profilebook.domain.models.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainProfileDto(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val title: String = "",
    val picture: String = ""
) : Parcelable
