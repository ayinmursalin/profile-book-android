package com.creativijaya.profilebook.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.creativijaya.profilebook.data.local.db.entities.post.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert
    suspend fun addFavoritePost(postEntity: PostEntity)

    @Query("SELECT * FROM posts WHERE post_id = :postId")
    fun checkIsFavorite(postId: String): Flow<MutableList<PostEntity>>

    @Query("SELECT * FROM posts")
    fun getFavoritePost(): Flow<MutableList<PostEntity>>

}
