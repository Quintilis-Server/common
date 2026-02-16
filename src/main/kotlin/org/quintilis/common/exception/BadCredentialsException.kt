package org.quintilis.common.exception;

public class BadCredentialsException extends BaseError {
    public BadCredentialsException() {
        super("Invalid email or password", ErrorCode.VALIDATION_ERROR);
    }
    public BadCredentialsException(String message) {
        super(message, ErrorCode.VALIDATION_ERROR);
    }
}
