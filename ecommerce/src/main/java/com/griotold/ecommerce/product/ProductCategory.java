package com.griotold.ecommerce.product;

import com.griotold.ecommerce.catelog.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    public static ProductCategory create(Product product, Category category, Boolean isPrimary) {
        return ProductCategory.builder()
                .product(product)
                .category(category)
                .isPrimary(isPrimary)
                .build();
    }
}
