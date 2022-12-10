package com.creativijaya.profilebook.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun addFriend(entity: FriendEntity)

    @Query("DELETE FROM friends WHERE profile_id = :profileId")
    suspend fun removeFriend(profileId: String)

    @Query("SELECT * FROM friends WHERE profile_id = :profileId")
    fun checkIsFriend(profileId: String): Flow<MutableList<FriendEntity>>

}
