package com.creativijaya.profilebook.domain.models.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasePaginationDto<T : Parcelable>(
    val data: List<T> = emptyList(),
    val totalData: Int = 0,
    val page: Int = 0
) : Parcelable
