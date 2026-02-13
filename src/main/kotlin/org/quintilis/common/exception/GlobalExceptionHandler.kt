package org.quintilis.common.exception

import org.quintilis.common.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseError::class)
    fun handleBaseError(baseError: BaseError): ResponseEntity<ApiResponse<String>> {
        val response = ApiResponse.error<String>(baseError.message!!, baseError.errorCode)
        return ResponseEntity<ApiResponse<String>>(response, HttpStatusCode.valueOf(baseError.errorCode.httpStatusCode))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ApiResponse<String>> {
        val response = ApiResponse.error<String>(exception.message!!, ErrorCode.INTERNAL_SERVER_ERROR)
        exception.printStackTrace()
        return ResponseEntity<ApiResponse<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}