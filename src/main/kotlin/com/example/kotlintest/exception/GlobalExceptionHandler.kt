package com.example.kotlintest.exception

import com.example.kotlintest.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 모든 예외를 전역적으로 처리하는 클래스
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 유효성 검증 실패 시 발생하는 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val firstErrorMessage = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage
            ?: "잘못된 입력입니다"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(firstErrorMessage)
    }

    /**
     * 사용자 정의 NotFoundException 처리
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    /**
     * 그 외 모든 예외 처리 (예: NullPointerException 등)
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("알 수 없는 오류가 발생했습니다: ${ex.localizedMessage}")
    }
}