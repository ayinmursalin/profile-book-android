package com.creativijaya.profilebook.domain.mapper

import com.creativijaya.profilebook.data.local.db.entities.post.PostEntity
import com.creativijaya.profilebook.data.local.db.entities.post.PostProfileEntity
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

    fun transformToPostEntity(
        dto: PostDto
    ) = PostEntity(
        postId = dto.id,
        image = dto.image,
        publishDate = dto.publishDate,
        text = dto.text,
        likes = dto.likes,
        tags = dto.tags,
        owner = PostProfileEntity(
            profileId = dto.owner.id,
            firstName = dto.owner.firstName,
            lastName = dto.owner.lastName,
            gender = dto.owner.gender
        )
    )

    fun transformToPostListDto(
        entities: List<PostEntity>?
    ) = entities?.map(::transformToPostDto).orEmpty()

    fun transformToPostDto(
        entity: PostEntity
    ) = PostDto(
        id = entity.postId.orEmpty(),
        image = entity.image.orEmpty(),
        publishDate = entity.publishDate.orEmpty(),
        text = entity.text.orEmpty(),
        likes = entity.likes.orZero(),
        tags = entity.tags.orEmpty(),
        owner = ProfileDto(
            id = entity.owner?.profileId.orEmpty(),
            firstName = entity.owner?.firstName.orEmpty(),
            lastName = entity.owner?.lastName.orEmpty(),
            gender = entity.owner?.gender.orEmpty()
        )
    )

}
