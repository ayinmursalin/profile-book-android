package com.creativijaya.profilebook.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.creativijaya.profilebook.MyAppConstant
import com.creativijaya.profilebook.data.local.db.daos.PostDao
import com.creativijaya.profilebook.data.local.db.daos.UserDao
import com.creativijaya.profilebook.data.local.db.entities.post.PostEntity
import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity

@Database(
    entities = [
        FriendEntity::class,
        PostEntity::class
    ],
    version = MyAppConstant.APP_DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(AppDbConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUser(): UserDao
    abstract fun getPostDao(): PostDao
}
