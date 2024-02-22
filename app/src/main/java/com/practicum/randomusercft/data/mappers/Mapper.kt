package com.practicum.randomusercft.data.mappers

import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.models.Result

fun Result.toModel(): UsersModel = UsersModel(
    fullName = "${this.name.title} " + "${this.name.first} " + this.name.last,
    gender = this.gender,
    country = "${this.location.country} " + "${this.location.city} " + this.location.state,
    email = this.email,
    picture = this.picture.medium
)