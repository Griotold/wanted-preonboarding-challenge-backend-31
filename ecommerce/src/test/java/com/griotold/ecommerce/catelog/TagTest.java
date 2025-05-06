package com.griotold.ecommerce.catelog;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TagTest {

    @Test
    @DisplayName("create 메서드로 태그를 생성할 수 있다")
    void create() {
        // given
        String name = "세일";
        String slug = "sale";

        // when
        Tag tag = Tag.create(name, slug);

        // then
        assertThat(tag.getName()).isEqualTo(name);
        assertThat(tag.getSlug()).isEqualTo(slug);
        assertThat(tag.getId()).isNull();
    }
}