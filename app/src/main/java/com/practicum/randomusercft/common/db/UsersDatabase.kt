package com.practicum.randomusercft.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.randomusercft.data.models.UsersModel

@Database(entities = [UsersModel::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {
    abstract val usersDao: UserDao
}