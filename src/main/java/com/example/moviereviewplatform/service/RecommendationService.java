package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDao;
import com.example.moviereviewplatform.entity.Movies;

import java.util.List;

public class RecommendationService {
    private static final RecommendationService INSTANCE = new RecommendationService();
    private final MovieDao movieDao = MovieDao.getInstance();

    public List<Movies> getTopRatedMovies() {
        return movieDao.getTopRatedMovies();
    }
    public static RecommendationService getInstance() {
        return INSTANCE;
    }
}