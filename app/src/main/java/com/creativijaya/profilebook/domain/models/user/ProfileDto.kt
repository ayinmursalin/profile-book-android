package com.creativijaya.profilebook.domain.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDto(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val title: String = "",
    val picture: String = "",
    val gender: String = "",
    val phone: String = "",
    val dateOfBirth: String = "",
    val updatedDate: String = "",
    val email: String = "",
    val registerDate: String = "",
    val location: LocationDto = LocationDto(),
) : Parcelable {
    val address: String
        get() {
            return "${location.street}, ${location.city}, ${location.city}, ${location.state}, ${location.country}"
        }
}
