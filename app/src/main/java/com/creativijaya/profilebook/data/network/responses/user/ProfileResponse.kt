package com.creativijaya.profilebook.data.network.responses.user

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
	@SerializedName("id")
	val id: String? = null,
	@SerializedName("firstName")
	val firstName: String? = null,
	@SerializedName("lastName")
	val lastName: String? = null,
	@SerializedName("gender")
	val gender: String? = null,
	@SerializedName("phone")
	val phone: String? = null,
	@SerializedName("dateOfBirth")
	val dateOfBirth: String? = null,
	@SerializedName("updatedDate")
	val updatedDate: String? = null,
	@SerializedName("title")
	val title: String? = null,
	@SerializedName("picture")
	val picture: String? = null,
	@SerializedName("email")
	val email: String? = null,
	@SerializedName("registerDate")
	val registerDate: String? = null,
	@SerializedName("location")
	val location: LocationResponse? = null,
)
