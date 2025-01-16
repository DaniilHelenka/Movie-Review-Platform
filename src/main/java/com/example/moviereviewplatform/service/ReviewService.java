package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDaoImpl;
import com.example.moviereviewplatform.dao.ReviewDaoImpl;
import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Reviews;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReviewService {
    private static final ReviewService INSTANCE = new ReviewService();

    private final ReviewDaoImpl reviewDao;
    private final MovieDaoImpl movieDao;

    private ReviewService() {
        this.reviewDao = ReviewDaoImpl.getInstance();
        this.movieDao = MovieDaoImpl.getInstance();
    }

    public void addReview(Integer userId, Integer movieId, Integer rating, String comments) {
        Optional<Movies> movieOptional = movieDao.findById(movieId);
        Movies movie = movieOptional.get();
        var review = Reviews.builder()
                .userId(userId)
                .movie(movie)
                .rating(rating)
                .comments(comments)
                .createdAt(LocalDateTime.now())
                .build();
        reviewDao.save(review);
    }

    public List<ReviewDto> findAllByMovieId(Integer movieId) {
        return reviewDao.findAllByMovieId(movieId).stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .movieId(movieId)
                        .rating(review.getRating())
                        .comments(review.getComments())
                        .created_at(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public static ReviewService getInstance() {
        return INSTANCE;
    }
}