package com.creativijaya.profilebook.data.repository

import com.creativijaya.profilebook.data.local.db.daos.UserDao
import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity
import com.creativijaya.profilebook.data.network.responses.base.BasePaginationResponse
import com.creativijaya.profilebook.data.network.responses.post.PostResponse
import com.creativijaya.profilebook.data.network.services.UserService
import com.creativijaya.profilebook.util.ext.successOrError
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val service: UserService,
    private val dao: UserDao
) : UserRepository {

    override suspend fun getProfile(
        page: Int,
        pageSize: Int
    ) = successOrError {
        service.getProfile(page, pageSize)
    }

    override suspend fun getProfileDetail(userId: String) = successOrError {
        service.getProfileDetail(userId)
    }

    override suspend fun getUserPosts(userId: String) = successOrError {
        service.getUserPosts(userId)
    }

    override suspend fun addFriend(entity: FriendEntity) {
        dao.addFriend(entity)
    }

    override suspend fun removeFriend(profileId: String) {
        dao.removeFriend(profileId)
    }

    override fun checkIsFriend(profileId: String): Flow<MutableList<FriendEntity>> {
        return dao.checkIsFriend(profileId)
    }
}
