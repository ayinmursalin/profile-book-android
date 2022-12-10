package com.creativijaya.profilebook.presentation.main.detailprofile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailProfileArgs(
    val userId: String = ""
) : Parcelable
