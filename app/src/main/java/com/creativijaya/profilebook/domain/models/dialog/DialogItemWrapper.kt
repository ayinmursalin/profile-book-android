package com.creativijaya.profilebook.domain.models.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogItemWrapper<T : Parcelable>(
    val itemIconResId: Int? = null,
    val itemLabel: String = "",
    val itemDescription: String? = null,
    var itemIsSelected: Boolean = false,
    val data: T? = null
) : Parcelable
