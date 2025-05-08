package com.griotold.ecommerce.common.response;

import java.util.List;

public record PageResponse<T>(
        boolean success,
        PageData<T> data,
        String message
) {
    public record PageData<T>(
            List<T> items,
            Pagination pagination
    ) {}

    public record Pagination(
            long totalItems,
            int totalPages,
            int currentPage,
            int perPage
    ) {}
}
