package org.quintilis.common.exception

class AlreadyExistsException(type: String) : BaseError("$type already exists", ErrorCode.EXISTS)
