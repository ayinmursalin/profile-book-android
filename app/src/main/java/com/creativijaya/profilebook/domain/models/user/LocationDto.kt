package com.creativijaya.profilebook.domain.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    val country: String = "",
    val city: String = "",
    val street: String = "",
    val timezone: String = "",
    val state: String = ""
) : Parcelable
