package com.griotold.ecommerce.review;

import com.griotold.ecommerce.product.Product;
import com.griotold.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "e_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer rating;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 실제 구매자가 남긴 리뷰인지 여부
    @Column(name = "verified_purchase")
    private Boolean verifiedPurchase;

    // 이 리뷰가 다른 사용자에게 얼마나 "도움이 되었다"고 평가받았는지
    @Column(name = "helpful_votes")
    private Integer helpfulVotes;

    public static Review create(Product product, User user, Integer rating, String title, String content,
                                LocalDateTime createdAt, LocalDateTime updatedAt,
                                Boolean verifiedPurchase, Integer helpfulVotes) {
        return Review.builder()
                .product(product)
                .user(user)
                .rating(rating)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .verifiedPurchase(verifiedPurchase)
                .helpfulVotes(helpfulVotes)
                .build();
    }
}
