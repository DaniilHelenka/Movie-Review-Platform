package com.example.moviereviewplatform.dao;

import java.util.List;
import java.util.Optional;

/**
 * @param <K>
 * @param <T>
 */
public interface Dao<K, T> {

    List<T> findAll();

    Optional<T> findById(K id);

    void save(T entity);

    boolean deleteById(K id);


}
