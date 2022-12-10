package com.creativijaya.profilebook.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.creativijaya.profilebook.MyAppConstant
import com.creativijaya.profilebook.data.local.db.daos.UserDao
import com.creativijaya.profilebook.data.local.db.entities.user.FriendEntity

@Database(
    entities = [
        FriendEntity::class
    ],
    version = MyAppConstant.APP_DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUser(): UserDao
}
