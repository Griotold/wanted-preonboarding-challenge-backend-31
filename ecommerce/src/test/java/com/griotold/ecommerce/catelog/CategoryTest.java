package com.griotold.ecommerce.catelog;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CategoryTest {

    @Test
    @DisplayName("create 메서드로 카테고리를 생성할 수 있다")
    void create() {
        // given
        String name = "가구";
        String slug = "furniture";
        String description = "편안한 생활을 위한 다양한 가구 컬렉션";
        Integer level = 1;
        String imageUrl = "https://example.com/categories/furniture.jpg";

        // when
        Category category = Category.create(name, slug, description, level, imageUrl);

        // then
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getSlug()).isEqualTo(slug);
        assertThat(category.getDescription()).isEqualTo(description);
        assertThat(category.getLevel()).isEqualTo(level);
        assertThat(category.getImageUrl()).isEqualTo(imageUrl);
        assertThat(category.getParent()).isNull();
        assertThat(category.getChildren()).isEmpty();
    }

    @Test
    @DisplayName("createWithParent 메서드로 부모가 있는 카테고리를 생성할 수 있다")
    void createWithParent() {
        // given
        // 1. 부모 카테고리 생성
        Category parentCategory = Category.create(
                "가구", "furniture", "편안한 생활을 위한 다양한 가구 컬렉션", 1,
                "https://example.com/categories/furniture.jpg");

        // 2. 자식 카테고리 정보
        String name = "소파";
        String slug = "sofa";
        String description = "다양한 디자인의 소파 모음";
        Integer level = 2;
        String imageUrl = "https://example.com/categories/sofa.jpg";

        // when
        Category childCategory = Category.createWithParent(
                name, slug, description, level, imageUrl, parentCategory);

        // then
        // 1. 자식 카테고리 속성 검증
        assertThat(childCategory.getName()).isEqualTo(name);
        assertThat(childCategory.getSlug()).isEqualTo(slug);
        assertThat(childCategory.getDescription()).isEqualTo(description);
        assertThat(childCategory.getLevel()).isEqualTo(level);
        assertThat(childCategory.getImageUrl()).isEqualTo(imageUrl);

        // 2. 부모-자식 양방향 관계 검증
        assertThat(childCategory.getParent()).isEqualTo(parentCategory);
        assertThat(parentCategory.getChildren()).contains(childCategory);
        assertThat(parentCategory.getChildren().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("createWithParent 메서드에 부모가 null일 경우 정상적으로 생성된다")
    void createWithNullParent() {
        // given
        String name = "가전제품";
        String slug = "electronics";
        String description = "생활의 편리함을 더하는 가전제품 모음";
        Integer level = 1;
        String imageUrl = "https://example.com/categories/electronics.jpg";

        // when
        Category category = Category.createWithParent(
                name, slug, description, level, imageUrl, null);

        // then
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getSlug()).isEqualTo(slug);
        assertThat(category.getDescription()).isEqualTo(description);
        assertThat(category.getLevel()).isEqualTo(level);
        assertThat(category.getImageUrl()).isEqualTo(imageUrl);
        assertThat(category.getParent()).isNull();
        assertThat(category.getChildren()).isEmpty();
    }

}