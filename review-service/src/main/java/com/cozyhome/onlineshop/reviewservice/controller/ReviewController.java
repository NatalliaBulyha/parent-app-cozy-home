package com.cozyhome.onlineshop.reviewservice.controller;

import com.cozyhome.onlineshop.reviewservice.controller.swagger.CommonApiResponses;
import com.cozyhome.onlineshop.reviewservice.dto.ReviewRequest;
import com.cozyhome.onlineshop.reviewservice.dto.ReviewResponse;
import com.cozyhome.onlineshop.reviewservice.model.Review;
import com.cozyhome.onlineshop.reviewservice.validation.ValidSkuCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Review")
@CommonApiResponses
@CrossOrigin({ "${api.front.base_url}", "${api.front.localhost}", "${api.front.test_url}",
        "${api.front.additional_url}" })
public interface ReviewController {

    ResponseEntity<List<ReviewResponse>> getReviews();

    ResponseEntity<ReviewResponse> saveReview(ReviewRequest review);

    ResponseEntity<List<ReviewResponse>> getReviewsForProduct(String productSkuCode);
}
