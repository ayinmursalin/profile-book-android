package com.creativijaya.profilebook.domain.mapper

import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import com.creativijaya.profilebook.data.network.responses.user.ProfileResponse
import com.creativijaya.profilebook.domain.models.base.BasePaginationDto
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.util.ext.orZero

class PostMapper {

    fun transformToPostListDto(
        response: BasePaginationResponse<PostResponse>,
        profileMapper: (ProfileResponse?) -> ProfileDto
    ): BasePaginationDto<PostDto> {
        return BasePaginationDto(
            totalData = response.total.orZero(),
            page = response.page.orZero(),
            data = response.data?.map {
                transformToPostDto(it, profileMapper)
            }.orEmpty()
        )
    }

    private fun transformToPostDto(
        response: PostResponse?,
        profileMapper: (ProfileResponse?) -> ProfileDto
    ) = PostDto(
        id = response?.id.orEmpty(),
        owner = profileMapper(response?.owner),
        image = response?.image.orEmpty(),
        publishDate = response?.publishDate.orEmpty(),
        text = response?.text.orEmpty(),
        likes = response?.likes.orZero(),
        tags = response?.tags.orEmpty()
    )

}
