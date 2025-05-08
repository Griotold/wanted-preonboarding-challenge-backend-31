package com.griotold.ecommerce.common.response;

import com.griotold.ecommerce.common.exception.EcommerceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public record ErrorResponse(
        boolean success,
        ErrorBody error
) {
    public record ErrorBody(
            String code,
            String message,
            Map<String, Object> details // 선택적 추가 정보
    ) {}

    public static ResponseEntity<ErrorResponse> fail(EcommerceException e) {
        ErrorBody errorBody = new ErrorBody(
                e.getHttpStatus().name(),      // 예: "BAD_REQUEST"
                e.getMessage(),
                null                           // 필요 시 상세정보 추가
        );
        ErrorResponse errorResponse = new ErrorResponse(false, errorBody);
        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }

    public static ResponseEntity<ErrorResponse> fail(HttpStatus status, String code, String message, Map<String, Object> details) {
        ErrorBody errorBody = new ErrorBody(code, message, details);
        ErrorResponse errorResponse = new ErrorResponse(false, errorBody);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
