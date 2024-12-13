package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll();
    Optional<T> findById(K id);

    List<Movies> getTopRatedMovies();

    T save(T entity);
    boolean deleteById(K id);


}
