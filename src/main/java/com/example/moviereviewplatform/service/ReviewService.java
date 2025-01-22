package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void addReview(Integer userId, Integer movieId, Integer rating, String comments);

    List<ReviewDto> findAllByMovieId(Integer movieId);
}
