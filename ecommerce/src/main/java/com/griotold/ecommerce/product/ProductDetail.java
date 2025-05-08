package com.griotold.ecommerce.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_product_detail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Double weight;

    @Column(columnDefinition = "TEXT")
    private String dimensions;

    @Column(columnDefinition = "TEXT")
    private String materials;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "warranty_info", columnDefinition = "TEXT")
    private String warrantyInfo;

    @Column(name = "care_instructions", columnDefinition = "TEXT")
    private String careInstructions;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    public static ProductDetail create(Product product, Double weight, String dimensions,
                                       String materials, String countryOfOrigin,
                                       String warrantyInfo, String careInstructions,
                                       String additionalInfo) {
        return ProductDetail.builder()
                .product(product)
                .weight(weight)
                .dimensions(dimensions)
                .materials(materials)
                .countryOfOrigin(countryOfOrigin)
                .warrantyInfo(warrantyInfo)
                .careInstructions(careInstructions)
                .additionalInfo(additionalInfo)
                .build();
    }
}
