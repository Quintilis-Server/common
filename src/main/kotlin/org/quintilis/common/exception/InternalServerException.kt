package org.quintilis.common.exception

class InternalServerException(message: String): BaseError(message, ErrorCode.INTERNAL_SERVER_ERROR)