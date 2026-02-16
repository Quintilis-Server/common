package org.quintilis.common.exception

class NotFoundException(message: String) : BaseError(message, ErrorCode.NOT_FOUND)
