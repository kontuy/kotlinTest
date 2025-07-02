package com.example.kotlintest.model

import jakarta.persistence.*

@Entity
@Table(name = "test_user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var email: String? = null
    var name: String? = null
}