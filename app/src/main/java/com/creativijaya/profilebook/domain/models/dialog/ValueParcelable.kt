package com.creativijaya.profilebook.domain.models.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IntParcelable(
    val data: Int = 0
) : Parcelable

@Parcelize
data class StringParcelable(
    val data: String = ""
) : Parcelable

@Parcelize
data class LongParcelable(
    val data: Long = 0L
) : Parcelable

@Parcelize
data class DoubleParcelable(
    val data: Double = 0.0
) : Parcelable

@Parcelize
data class BoolParcelable(
    val data: Boolean = false
) : Parcelable
