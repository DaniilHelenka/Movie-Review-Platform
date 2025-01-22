package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Reviews;

public class ReviewMapper {
    private static final ReviewMapper INSTANCE = new ReviewMapper();

    private ReviewMapper() {
    }

    public static ReviewMapper getInstance() {
        return INSTANCE;
    }

    public ReviewDto mapFrom(Reviews review, Integer movieId) {
        return ReviewDto.builder()
                .id(review.getId())
                .movieId(movieId)
                .rating(review.getRating())
                .comments(review.getComments())
                .created_at(review.getCreatedAt())
                .build();
    }
}