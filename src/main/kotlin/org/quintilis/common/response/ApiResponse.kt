package org.quintilis.common.response

import lombok.Builder
import lombok.NoArgsConstructor
import org.quintilis.common.exception.ErrorCode
import java.io.Serializable
import java.time.Instant

@Builder
@NoArgsConstructor
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val timestamp: Instant = Instant.now(),
    val errorCode: ErrorCode? = null
) : Serializable {
    constructor(message: String, errorCode: ErrorCode): this(success = false, message = message, errorCode = errorCode)

    companion object{
        fun <T>error(error: String, errorCode: ErrorCode): ApiResponse<T> {
            return ApiResponse<T>(message = error, errorCode = errorCode)
        }

        fun <T> success(data: T, message: String? = null): ApiResponse<T>{
            return ApiResponse(true, data, message = message)
        }
    }
}