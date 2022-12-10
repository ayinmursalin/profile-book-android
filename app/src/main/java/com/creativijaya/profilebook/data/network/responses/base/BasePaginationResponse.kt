package com.creativijaya.profilebook.data.network.responses.base

import com.google.gson.annotations.SerializedName

data class BasePaginationResponse<T>(
    @SerializedName("data")
    val data: List<T>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
)

