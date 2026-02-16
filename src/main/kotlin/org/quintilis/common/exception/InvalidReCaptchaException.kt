package org.quintilis.common.exception

class InvalidReCaptchaException(message: String) : BaseError(message, ErrorCode.INVALID_CAPTCHA)
