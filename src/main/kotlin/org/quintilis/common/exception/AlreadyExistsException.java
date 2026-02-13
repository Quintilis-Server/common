package org.quintilis.common.exception;

public class AlreadyExistsException extends BaseError {
    public AlreadyExistsException(String type) {
        super(type+ " already exists ", ErrorCode.EXISTS);
    }
}
