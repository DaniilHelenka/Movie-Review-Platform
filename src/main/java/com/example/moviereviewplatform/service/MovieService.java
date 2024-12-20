package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDao;
import com.example.moviereviewplatform.dao.WatchlistDao;
import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.mapper.CreateMovieMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();

    private final MovieDao movieDao = MovieDao.getInstance();
    private final CreateMovieMapper createMovieMapper = CreateMovieMapper.getInstance();
    private final  ImageService imageService = ImageService.getInstance();
    public MovieService() {
    }

    public List<MovieDto> findAll(){
        return movieDao.findAll().stream()
                .map(movies -> new MovieDto(
                        movies.getId(),
                        movies.getName(),
                        movies.getGenre(),
                        movies.getDescription(),
                        movies.getPoster_url(),
                        movies.getRelease_date()
                ))
                .collect(toList());
    }
    public Optional<MovieDto> findById(Integer id) {
        return movieDao.findById(id)
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getName(),
                        movie.getGenre(),
                        movie.getDescription(),
                        movie.getPoster_url(),
                        movie.getRelease_date()
                ));
    }

    public void addMovie(CreateMovieDto movieDto) throws IOException {


        var movieEntity = createMovieMapper.mapFrom(movieDto);
        imageService.upload(movieEntity.getPoster_url(), movieDto.getPoster_url().getInputStream());
        movieDao.save(movieEntity);
    }
    public boolean deleteMovie(Integer id) {
        return movieDao.deleteById(id);
    }
    public void moveToWatched(int movieId) {
        // 1. Найти запись в Watchlist с данным movieId и типом "watching"
        WatchlistDao watchlistDao = new WatchlistDao();
        Watchlist watchlistItem = watchlistDao.findByMovieIdAndListType(movieId, "watching");

        if (watchlistItem != null) {
            // 2. Обновить список с "watching" на "watched"
            watchlistItem.setListType("watched");
            watchlistDao.update(watchlistItem); // Обновляем запись в базе
        } else {
            throw new IllegalArgumentException("Фильм не найден в списке 'буду смотреть'");
        }
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }
}
