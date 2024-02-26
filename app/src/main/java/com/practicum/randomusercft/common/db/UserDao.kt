package com.practicum.randomusercft.common.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicum.randomusercft.data.models.UsersModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UsersModel)

    @Delete
    suspend fun delete(user: UsersModel)

    @Query("SELECT * FROM UsersModel")
    fun getUsers(): Flow<List<UsersModel>>
}
