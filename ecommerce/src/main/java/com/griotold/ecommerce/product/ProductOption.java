package com.griotold.ecommerce.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_product_option")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    private ProductOptionGroup optionGroup;

    @Column(nullable = false)
    private String name;

    @Column(name = "additional_price")
    private Double additionalPrice;

    private String sku;

    private Integer stock;

    @Column(name = "display_order")
    private Integer displayOrder;

    public static ProductOption create(ProductOptionGroup optionGroup, String name, Double additionalPrice,
                                       String sku, Integer stock, Integer displayOrder) {
        return ProductOption.builder()
                .optionGroup(optionGroup)
                .name(name)
                .additionalPrice(additionalPrice)
                .sku(sku)
                .stock(stock)
                .displayOrder(displayOrder)
                .build();
    }
}
