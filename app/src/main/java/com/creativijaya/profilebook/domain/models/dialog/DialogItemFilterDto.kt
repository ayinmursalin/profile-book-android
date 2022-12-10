package com.creativijaya.profilebook.domain.models.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogItemFilterDto(
    val key: String = "",
    val label: String = "",
    val icon: Int? = null
) : Parcelable
