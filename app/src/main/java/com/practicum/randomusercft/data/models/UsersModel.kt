package com.practicum.randomusercft.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersModel(
    val fullName: String,
    val country: String,
    val email: String,
    val picture: String,
    val phone: String,
    val coordinates: String
): Parcelable