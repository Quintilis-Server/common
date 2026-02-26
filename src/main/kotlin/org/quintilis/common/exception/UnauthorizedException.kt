package org.quintilis.common.exception

class UnauthorizedException(override val message: String): BaseError(message, ErrorCode.UNAUTHORIZED)