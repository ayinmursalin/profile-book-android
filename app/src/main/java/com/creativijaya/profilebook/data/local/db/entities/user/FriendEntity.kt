package com.creativijaya.profilebook.data.local.db.entities.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class FriendEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "profile_id")
    val profileId: String? = null,
    @ColumnInfo(name = "firstName")
    val firstName: String? = null,
    @ColumnInfo(name = "lastName")
    val lastName: String? = null,
    @ColumnInfo(name = "gender")
    val gender: String? = null,
    @ColumnInfo(name = "phone")
    val phone: String? = null,
    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String? = null,
    @ColumnInfo(name = "updatedDate")
    val updatedDate: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "picture")
    val picture: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "registerDate")
    val registerDate: String? = null,
    @Embedded
    val locationEntity: LocationEntity? = null,
)
