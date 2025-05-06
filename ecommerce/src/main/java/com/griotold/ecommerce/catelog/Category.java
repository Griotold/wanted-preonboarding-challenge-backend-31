package com.griotold.ecommerce.catelog;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    private Integer level;
    private String imageUrl;

    public static Category create(String name, String slug, String description, Integer level, String imageUrl) {
        return Category.builder()
                .name(name)
                .slug(slug)
                .description(description)
                .level(level)
                .imageUrl(imageUrl)
                .build();
    }

    // 부모가 있는 카테고리 생성 메서드
    public static Category createWithParent(String name, String slug, String description,
                                            Integer level, String imageUrl, Category parent) {
        Category category = Category.builder()
                .name(name)
                .slug(slug)
                .description(description)
                .level(level)
                .imageUrl(imageUrl)
                .parent(parent)
                .build();

        if (parent != null) {
            parent.getChildren().add(category);
        }

        return category;
    }
}
