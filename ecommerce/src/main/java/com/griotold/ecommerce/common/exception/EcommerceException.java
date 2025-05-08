package com.griotold.ecommerce.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EcommerceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public EcommerceException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
