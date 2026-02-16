package org.quintilis.common.exception

class BadRequestException(
    message: String,
    val userError: String
) : BaseError(message, ErrorCode.BAD_REQUEST)
