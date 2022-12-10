package com.creativijaya.profilebook.data.network.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("error")
    val error: Boolean? = null,
    @SerializedName("code")
    val code: Int? = null
)

