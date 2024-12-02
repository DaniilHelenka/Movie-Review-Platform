package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDao;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Movies;

import java.time.LocalDateTime;
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
                        movies.getGenre(),
                        movies.getDescription(),
                        movies.getPoster_url()
                ))
                .collect(toList());
    }

    public void addMovie(String name, String genre, String description, String poster_url, LocalDateTime release_date) {
        Movies movie = new Movies();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setPoster_url(poster_url);
        movie.setRelease_date(release_date);

        movieDao.save(movie);
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }
}
