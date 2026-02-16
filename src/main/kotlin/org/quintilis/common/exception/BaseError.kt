package org.quintilis.common.exception

abstract class BaseError(
    message: String,
    val errorCode: ErrorCode
) : RuntimeException(message)
