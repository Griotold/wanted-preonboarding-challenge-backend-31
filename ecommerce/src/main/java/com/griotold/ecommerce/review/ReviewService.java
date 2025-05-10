package com.griotold.ecommerce.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
class ReviewService {

    void createReview(ReviewCreateRequest request) {
        log.info("createReview.ReviewCreateRequest: {}", request);

    }
}
