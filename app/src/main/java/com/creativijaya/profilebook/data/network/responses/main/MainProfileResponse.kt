package com.creativijaya.profilebook.data.network.responses.main

import com.google.gson.annotations.SerializedName

data class MainProfileResponse(
	@SerializedName("firstName")
	val firstName: String? = null,
	@SerializedName("lastName")
	val lastName: String? = null,
	@SerializedName("id")
	val id: String? = null,
	@SerializedName("title")
	val title: String? = null,
	@SerializedName("picture")
	val picture: String? = null
)
