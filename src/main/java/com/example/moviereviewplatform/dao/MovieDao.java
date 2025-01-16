package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;

import java.util.List;

public interface MovieDao extends Dao<Integer, Movies> {
    List<Movies> getTopRatedMovies();
}
