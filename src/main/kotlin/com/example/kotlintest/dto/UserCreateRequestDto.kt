package com.example.kotlintest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserCreateRequestDto(
    @field:NotBlank(message = "이름은 필수값입니다.")
    val name: String,

    @field:Email(message = "이메일 형식을 확인해주세요.")
    val email: String
)