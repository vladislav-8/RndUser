package com.practicum.randomusercft.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * основная моделька пользователя
 */
@Parcelize
@Entity
data class UsersModel(
    @PrimaryKey
    val fullName: String,
    val country: String,
    val email: String,
    val picture: String,
    val phone: String,
    val coordinates: String
): Parcelable