package com.example.moviereviewplatform.service.impl;

import com.example.moviereviewplatform.dao.impl.MovieDaoImpl;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.service.RecommendationService;

import java.util.List;

public class RecommendationServiceImpl implements RecommendationService {
    private static final RecommendationServiceImpl INSTANCE = new RecommendationServiceImpl();

    private RecommendationServiceImpl() {
    }
    private final MovieDaoImpl movieDao = MovieDaoImpl.getInstance();
    public List<Movies> getTopRatedMovies() {
        return movieDao.getTopRatedMovies();
    }
    public static RecommendationServiceImpl getInstance() {
        return INSTANCE;
    }
}