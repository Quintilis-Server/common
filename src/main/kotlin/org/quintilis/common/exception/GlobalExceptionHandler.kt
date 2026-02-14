package org.quintilis.common.exception

import org.quintilis.common.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException

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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity(ApiResponse.error(exception.localizedMessage, ErrorCode.BAD_REQUEST), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<ApiResponse<String>> {
        val response: ApiResponse<String> = ApiResponse.error(ex.message!!, ex.errorCode)
        return ResponseEntity(
            response,
            HttpStatusCode.valueOf(ex.errorCode.httpStatusCode)
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(ex: NoResourceFoundException): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity(ApiResponse.error(ex.message!!, ErrorCode.NOT_FOUND), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFound(ex: NoHandlerFoundException): ResponseEntity<ApiResponse<String>> {
        val response: ApiResponse<String> = ApiResponse.error(
            "Endpoint não encontrado: " + ex.requestURL,
            ErrorCode.NOT_FOUND
        )
        return ResponseEntity<ApiResponse<String>>(response, HttpStatus.NOT_FOUND)
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
//    fun handleMethodNotSupported(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ApiResponse<Any?>?> {
//        val response: ApiResponse<Any?>? = ApiResponse.error(
//            "Método " + ex.method.toString() + " não suportado",
//            ErrorCode.BAD_REQUEST
//        )
//        return ResponseEntity<ApiResponse<Any?>?>(response, HttpStatus.METHOD_NOT_ALLOWED)
//    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<String>> {
        val message = ex.getBindingResult().getFieldErrors().stream()
            .map<String?> { err: FieldError? -> err!!.getField() + ": " + err.getDefaultMessage() }
            .findFirst()
            .orElse("Validation error")

        return ResponseEntity<ApiResponse<String>>(ApiResponse.error<String>(message, ErrorCode.VALIDATION_ERROR), HttpStatus.BAD_REQUEST)
    }
}