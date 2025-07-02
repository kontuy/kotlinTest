package com.example.kotlintest.service

import com.example.kotlintest.model.User
import com.example.kotlintest.repository.UserRepository
import com.example.kotlintest.dto.UserCreateRequestDto
import com.example.kotlintest.mapper.toResponseDto
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun createUser(dto: UserCreateRequestDto): User {
        val user = User().apply {
                name = dto.name
                email = dto.email
        }
        return userRepository.save(user)
    }

    fun updateUser(id: Long, updatedUser: User): User?  {
        return userRepository.findById(id).map { existingUser ->
            existingUser.name = updatedUser.name
            existingUser.email = updatedUser.email
            userRepository.save(existingUser)
        }.orElse(null)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}