package com.creativijaya.profilebook.domain.mapper

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.main.MainProfileResponse
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.util.ext.orZero

class MainMapper {

    fun transformToProfileBookListDto(
        response: BasePaginationResponse<MainProfileResponse>
    ): BasePaginationDto<ProfileDto> {
        return BasePaginationDto(
            totalData = response.total.orZero(),
            page = response.page.orZero(),
            data = response.data?.map(::transformToProfileBookDto).orEmpty()
        )
    }

    private fun transformToProfileBookDto(
        response: MainProfileResponse
    ) = ProfileDto(
        id = response.id.orEmpty(),
        firstName = response.firstName.orEmpty(),
        lastName = response.lastName.orEmpty(),
        title = response.title.orEmpty(),
        picture = response.picture.orEmpty(),
    )

}
