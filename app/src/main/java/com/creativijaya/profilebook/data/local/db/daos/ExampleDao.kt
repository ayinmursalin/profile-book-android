package com.creativijaya.profilebook.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.creativijaya.profilebook.data.local.db.entities.ExampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExample(entity: ExampleEntity)

    @Query("SELECT * FROM examples")
    fun getExamples(): Flow<List<ExampleEntity>>

}
