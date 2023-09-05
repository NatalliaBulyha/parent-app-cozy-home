package com.cozyhome.onlineshop.reviewservice.service;

import com.cozyhome.onlineshop.reviewservice.dto.ReviewRequest;
import com.cozyhome.onlineshop.reviewservice.dto.ReviewResponse;
import com.cozyhome.onlineshop.reviewservice.model.Review;
import com.cozyhome.onlineshop.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<ReviewResponse> getReviews() {
        return repository.findAll().stream().map(review -> mapper.map(review, ReviewResponse.class)).toList();
    }

    @Override
    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        Review review = mapper.map(reviewRequest, Review.class);
        review.setCreatedAt(LocalDateTime.now());
        review.setModifiedAt(LocalDateTime.now());
        return mapper.map(repository.save(review), ReviewResponse.class);
    }

    @Override
    public List<ReviewResponse> getReviewsForProduct(String productSkuCode) {
        List<Review> reviews = repository.findReviewsByProductSkuCode(productSkuCode);
        return reviews.stream().map(review -> mapper.map(review, ReviewResponse.class)).toList();
    }
}
