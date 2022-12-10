package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity
import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import com.creativijaya.profilebook.data.network.responses.user.ProfileResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface UserRepository {
    suspend fun getProfile(page: Int, pageSize: Int = 20): BasePaginationResponse<ProfileResponse>
    suspend fun getProfileDetail(userId: String): ProfileResponse
    suspend fun getUserPosts(userId: String): BasePaginationResponse<PostResponse>
    suspend fun addFriend(entity: FriendEntity)
    suspend fun removeFriend(profileId: String)
    fun checkIsFriend(profileId: String): Flow<MutableList<FriendEntity>>
}
