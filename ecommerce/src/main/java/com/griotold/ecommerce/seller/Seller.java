package com.griotold.ecommerce.seller;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    private Double rating;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static Seller create(String name, String description, String logoUrl,
                                Double rating, String contactEmail, String contactPhone, LocalDateTime createdAt) {
        return Seller.builder()
                .name(name)
                .description(description)
                .logoUrl(logoUrl)
                .rating(rating)
                .contactEmail(contactEmail)
                .contactPhone(contactPhone)
                .createdAt(createdAt)
                .build();
    }
}
