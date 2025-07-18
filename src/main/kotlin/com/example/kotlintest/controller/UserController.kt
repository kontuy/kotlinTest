package com.example.kotlintest.controller

import com.example.kotlintest.model.User
import com.example.kotlintest.service.UserService
import com.example.kotlintest.dto.UserCreateRequestDto
import com.example.kotlintest.dto.UserResponseDto
import com.example.kotlintest.mapper.toResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService  // ✅ 오타 수정
) {

    @GetMapping
    fun getAllUsers(): List<UserResponseDto> =
        userService.getAllUsers().map { it.toResponseDto() }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User? =
        userService.getUserById(id)  // ✅ 인스턴스 사용

    @GetMapping("/page")
    fun getUsersPage(pageable: Pageable): Page<UserResponseDto> {
        return userService.getUsersPage(pageable)
        .map { it.toResponseDto() }
    }

    @GetMapping("/search")
    fun searchUsers(
        @RequestParam name: String, pageable: Pageable): Page<UserResponseDto> {
            return userService.searchUserByName(name, pageable)
            .map { it.toResponseDto() }
        }
    

    // 한명에게만 전달 받을 때
/* 
    @PostMapping
    fun createUser(@Valid @RequestBody dto: UserCreateRequestDto): UserResponseDto {
        val savedUser = userService.createUser(dto)
        return savedUser.toResponseDto()
    }
*/
    @PostMapping("/batch")
    fun createUsers(@RequestBody dtos: List<UserCreateRequestDto>): List<UserResponseDto> {
        return dtos.map { dto ->
            val saved = userService.createUser(dto)
            saved.toResponseDto()
        }
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): User? =
        userService.updateUser(id, updatedUser)  // ✅ 인스턴스 사용

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)  // ✅ 인스턴스 사용
    }
}