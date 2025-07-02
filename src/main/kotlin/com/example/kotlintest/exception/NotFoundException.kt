package com.example.kotlintest.exception

/**
 * 특정 리소스를 찾을 수 없을 때 사용하는 예외
 */
class NotFoundException(message: String) : RuntimeException(message)