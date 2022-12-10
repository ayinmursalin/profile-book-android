package com.creativijaya.profilebook.data.network.responses.post

import com.creativijaya.profilebook.data.network.responses.user.ProfileResponse
import com.google.gson.annotations.SerializedName

data class PostResponse(
	@SerializedName("id")
	val id: String? = null,
	@SerializedName("owner")
	val owner: ProfileResponse? = null,
	@SerializedName("image")
	val image: String? = null,
	@SerializedName("publishDate")
	val publishDate: String? = null,
	@SerializedName("text")
	val text: String? = null,
	@SerializedName("likes")
	val likes: Int? = null,
	@SerializedName("tags")
	val tags: List<String>? = null
)
