package com.creativijaya.profilebook.domain.mapper

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.user.LocationResponse
import com.creativijaya.profilebook.data.network.responses.user.ProfileResponse
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.user.LocationDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.util.ext.orZero

class UserMapper {

    fun transformToProfileBookListDto(
        response: BasePaginationResponse<ProfileResponse>
    ): BasePaginationDto<ProfileDto> {
        return BasePaginationDto(
            totalData = response.total.orZero(),
            page = response.page.orZero(),
            data = response.data?.map(::transformToDetailProfileDto).orEmpty()
        )
    }

    fun transformToDetailProfileDto(
        response: ProfileResponse
    ) = ProfileDto(
        id = response.id.orEmpty(),
        firstName = response.firstName.orEmpty(),
        lastName = response.lastName.orEmpty(),
        title = response.title.orEmpty(),
        picture = response.picture.orEmpty(),
        gender = response.gender.orEmpty(),
        phone = response.phone.orEmpty(),
        dateOfBirth = response.dateOfBirth.orEmpty(),
        updatedDate = response.updatedDate.orEmpty(),
        email = response.email.orEmpty(),
        registerDate = response.registerDate.orEmpty(),
        location = transformToLocationDto(response.location),
    )

    private fun transformToLocationDto(
        response: LocationResponse?
    ) = LocationDto(
        country = response?.country.orEmpty(),
        city = response?.city.orEmpty(),
        street = response?.street.orEmpty(),
        timezone = response?.timezone.orEmpty(),
        state = response?.state.orEmpty()
    )

}
