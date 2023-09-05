package com.cozyhome.onlineshop.reviewservice.controller;

import com.cozyhome.onlineshop.reviewservice.controller.swagger.CommonApiResponses;
import com.cozyhome.onlineshop.reviewservice.dto.ReviewResponse;
import com.cozyhome.onlineshop.reviewservice.model.Review;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Review")
@CommonApiResponses
public interface ReviewController {

    public ResponseEntity<List<ReviewResponse>> getReviews();

    public ResponseEntity<ReviewResponse> saveReview(Review review);
}
