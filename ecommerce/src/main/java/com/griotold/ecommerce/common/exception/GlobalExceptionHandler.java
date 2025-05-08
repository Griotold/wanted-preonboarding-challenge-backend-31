package com.griotold.ecommerce.common.exception;

import com.griotold.ecommerce.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String ERROR_LOG = "[ERROR] %s %s";

    @ExceptionHandler(EcommerceException.class)
    public ResponseEntity<ErrorResponse> applicationException(final EcommerceException e){
        log.error(String.format(ERROR_LOG, e.getHttpStatus(), e.getMessage()));
        return ErrorResponse.fail(e);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error(String.format(ERROR_LOG, e.getMessage(), e.getClass().getName()));
        return ErrorResponse.fail(
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND",
                "요청하신 경로를 찾을 수 없습니다.",
                null
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpReqMethodNotSupportException(final HttpRequestMethodNotSupportedException e){
        log.error(String.format(ERROR_LOG, e.getMessage(), e.getSupportedMethods()));
        Map<String, Object> details = new HashMap<>();
        details.put("supportedMethods", e.getSupportedMethods());
        return ErrorResponse.fail(
                HttpStatus.METHOD_NOT_ALLOWED,
                "METHOD_NOT_ALLOWED",
                "요청 방법(GET/POST 등)이 지원되지 않습니다.",
                details
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingServletRequestParameter(final MissingServletRequestParameterException e) {
        log.error(String.format(ERROR_LOG, e.getParameterName(), e.getMessage()));
        Map<String, Object> details = new HashMap<>();
        details.put("parameterName", e.getParameterName());
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "INVALID_INPUT",
                "필요한 파라미터가 입력되지 않았습니다.",
                details
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(final MethodArgumentNotValidException e){
        log.error(String.format(ERROR_LOG, e.getParameter(), e.getStatusCode()));
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "INVALID_INPUT",
                errorMessage,
                null
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e){
        log.error(String.format(ERROR_LOG, e.getParameter(), HttpStatus.BAD_REQUEST));
        Map<String, Object> details = new HashMap<>();
        details.put("parameterName", e.getName());
        details.put("requiredType", e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : null);
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "INVALID_INPUT",
                "파라미터의 타입이 일치하지 않습니다.",
                details
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(final HttpMessageNotReadableException e){
        log.error(String.format(ERROR_LOG, e.getMessage(), e.getClass().getName()));
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "INVALID_INPUT",
                "잘못된 JSON 형식입니다.",
                null
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException e) {
        log.error(String.format(ERROR_LOG, e.getSQLException().getMessage(), e.getConstraintName()));
        Map<String, Object> details = new HashMap<>();
        details.put("constraintName", e.getConstraintName());
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "CONFLICT",
                "데이터베이스 제약 조건 위반: " + (e.getConstraintName() != null ? e.getConstraintName() : "알 수 없음"),
                details
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        log.error(String.format(ERROR_LOG, e.getMessage(), e.getClass().getName()));
        return ErrorResponse.fail(
                HttpStatus.BAD_REQUEST,
                "CONFLICT",
                "데이터베이스 무결성 위반: 중복된 값 또는 잘못된 데이터가 입력되었습니다.",
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(final Exception e) {
        log.error("[ERROR] Unexpected exception: {}", e.getMessage());
        return ErrorResponse.fail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "서버 내부 오류가 발생했습니다.",
                null
        );
    }
}
