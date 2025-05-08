package com.griotold.ecommerce.common.response;

public record ApiResponse<T>(
        boolean success,
        T data,
        String message
) {}
