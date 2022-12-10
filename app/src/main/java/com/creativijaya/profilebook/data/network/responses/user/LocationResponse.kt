package com.creativijaya.profilebook.data.network.responses.user

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("state")
    val state: String? = null
)
