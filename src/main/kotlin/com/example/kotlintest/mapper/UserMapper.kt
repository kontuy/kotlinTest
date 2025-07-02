package com.example.kotlintest.mapper

import com.example.kotlintest.model.User
import com.example.kotlintest.dto.UserResponseDto

fun User.toResponseDto(): UserResponseDto {
    return UserResponseDto(
        id = this.id ?: -1,
        name = this.name ?: "",
        email = this.email ?: ""
    )
}