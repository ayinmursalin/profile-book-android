package com.creativijaya.profilebook.domain.models.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogImageSourceDto(
    val type: DialogImageSourceType = DialogImageSourceType.CAMERA,
    val label: String = ""
) : Parcelable
