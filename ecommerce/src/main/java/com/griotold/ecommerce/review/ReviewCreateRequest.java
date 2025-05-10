package com.griotold.ecommerce.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * {
 *  "rating": 5,
 *  "title": "완벽한 소파입니다!",
 *  "content": "배송도 빠르고 품질도 매우 좋습니다. 색상도 사진과 동일하고 조립도 쉬웠어요."
 * }
 * */
public record ReviewCreateRequest(
    @NotNull(message = "price 는 필수 입력값입니다.")
    @Positive(message = "price 는 0보다 커야 합니다.")
    Integer rating,
    @NotBlank(message = "title 은 필수 입력값입니다.")
    String title,
    @NotBlank(message = "content 는 필수 입력값입니다.")
    String content
) {
}
