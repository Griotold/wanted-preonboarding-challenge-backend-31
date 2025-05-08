package com.griotold.ecommerce.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_product_option_group")
public class ProductOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(name = "display_order")
    private Integer displayOrder;

    public static ProductOptionGroup create(Product product, String name, Integer displayOrder) {
        return ProductOptionGroup.builder()
                .product(product)
                .name(name)
                .displayOrder(displayOrder)
                .build();
    }
}
