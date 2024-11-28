package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDao;
import com.example.moviereviewplatform.dto.MovieDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();

    private final MovieDao movieDao = MovieDao.getInstance();

    public MovieService() {
    }

    public List<MovieDto> findAll(){
        return movieDao.findAll().stream()
                .map(movies -> new MovieDto(
                        movies.getId(),
                        movies.getName(),
                        movies.getDescription(),
                        movies.getPoster_url()
                ))
                .collect(toList());
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }
}
