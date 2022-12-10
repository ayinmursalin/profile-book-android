package com.creativijaya.profilebook.domain.models.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainUserDto(
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    val gender: String = "",
    val birthDate: String = ""
) : Parcelable
