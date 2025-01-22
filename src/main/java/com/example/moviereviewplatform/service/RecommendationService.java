package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.entity.Movies;

import java.util.List;

public interface RecommendationService {
    List<Movies> getTopRatedMovies();
}
