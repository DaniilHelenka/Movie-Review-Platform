package com.example.moviereviewplatform.service.impl;

import com.example.moviereviewplatform.dao.impl.MovieDaoImpl;
import com.example.moviereviewplatform.dao.impl.ReviewDaoImpl;
import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.mapper.ReviewMapper;
import com.example.moviereviewplatform.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();

    private final ReviewDaoImpl reviewDao;
    private final MovieDaoImpl movieDao;
    private final ReviewMapper reviewMapper;

    private ReviewServiceImpl() {
        this.reviewDao = ReviewDaoImpl.getInstance();
        this.movieDao = MovieDaoImpl.getInstance();
        this.reviewMapper = ReviewMapper.getInstance();
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
                .map(review -> reviewMapper.mapFrom(review, movieId))
                .collect(Collectors.toList());
    }

    public static ReviewServiceImpl getInstance() {
        return INSTANCE;
    }
}