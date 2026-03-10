package org.quintilis.common.exception

class MethodNotAllowException(message: String) : BaseError(message, ErrorCode.METHOD_NOT_ALLOWED) {
}