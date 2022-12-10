package com.creativijaya.profilebook.domain.mapper

import androidx.room.ColumnInfo
import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity
import com.creativijaya.profilebook.data.local.db.entities.user.LocationEntity
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
        response: ProfileResponse?
    ) = ProfileDto(
        id = response?.id.orEmpty(),
        firstName = response?.firstName.orEmpty(),
        lastName = response?.lastName.orEmpty(),
        title = response?.title.orEmpty(),
        picture = response?.picture.orEmpty(),
        gender = response?.gender.orEmpty(),
        phone = response?.phone.orEmpty(),
        dateOfBirth = response?.dateOfBirth.orEmpty(),
        updatedDate = response?.updatedDate.orEmpty(),
        email = response?.email.orEmpty(),
        registerDate = response?.registerDate.orEmpty(),
        location = transformToLocationDto(response?.location),
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

    fun transformToUserEntity(
        dto: ProfileDto
    ) = FriendEntity(
        profileId = dto.id,
        firstName = dto.firstName,
        lastName = dto.lastName,
        title = dto.title,
        picture = dto.picture,
        gender = dto.gender,
        phone = dto.phone,
        dateOfBirth = dto.dateOfBirth,
        updatedDate = dto.updatedDate,
        email = dto.email,
        registerDate = dto.registerDate,
        locationEntity = LocationEntity(
            country = dto.location.country,
            city = dto.location.city,
            street = dto.location.street,
            timezone = dto.location.timezone,
            state = dto.location.state
        )
    )

}
