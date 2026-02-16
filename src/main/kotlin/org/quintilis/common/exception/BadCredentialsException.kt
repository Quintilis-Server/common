package org.quintilis.common.exception

class BadCredentialsException(message: String = "Invalid email or password") : BaseError(message, ErrorCode.VALIDATION_ERROR)
