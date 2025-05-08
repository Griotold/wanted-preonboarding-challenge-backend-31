package com.griotold.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    ACTIVE("판매중"),
    INACTIVE("판매중지")
    ;

    private final String description;
}
