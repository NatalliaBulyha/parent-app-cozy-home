package com.cozyhome.onlineshop.reviewservice.service;

import com.cozyhome.onlineshop.reviewservice.dto.ReviewRequest;
import com.cozyhome.onlineshop.reviewservice.dto.ReviewResponse;
import com.cozyhome.onlineshop.reviewservice.model.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getReviews();

    ReviewResponse saveReview(ReviewRequest review);

    List<ReviewResponse> getReviewsForProduct(String productSkuCode);
}
