package com.creativijaya.profilebook.domain.models.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogDateContainer(
    val day: Int,
    val month: Int,
    val year: Int
) : Parcelable
