package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Reviews;

import java.util.List;

public interface ReviewDao extends Dao<Integer, Reviews> {
    List<Reviews> findAllByMovieId(Integer movieId);
}