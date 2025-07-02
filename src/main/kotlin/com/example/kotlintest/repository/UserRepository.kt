package com.example.kotlintest.repository

import com.example.kotlintest.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>