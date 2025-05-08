package com.griotold.ecommerce.brand;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    private String website;

    public static Brand create(String name, String slug, String description, String logoUrl, String website) {
        return Brand.builder()
                .name(name)
                .slug(slug)
                .description(description)
                .logoUrl(logoUrl)
                .website(website)
                .build();
    }

}
