package com.creativijaya.profilebook.data.local.db.entities.post

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "post_id")
    val postId: String? = null,
    @ColumnInfo(name = "image")
    val image: String? = null,
    @ColumnInfo(name = "publishDate")
    val publishDate: String? = null,
    @ColumnInfo(name = "text")
    val text: String? = null,
    @ColumnInfo(name = "likes")
    val likes: Int? = null,
    @ColumnInfo(name = "tags")
    val tags: List<String>? = null,
    @Embedded
    val owner: PostProfileEntity? = null,
)
