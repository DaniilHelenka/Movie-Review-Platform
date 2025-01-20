package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDaoImpl;
import com.example.moviereviewplatform.dao.WatchlistDaoImpl;
import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.mapper.CreateMovieMapper;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();

    private final MovieDaoImpl movieDaoImpl;
    private final CreateMovieMapper createMovieMapper;

    private MovieService() {
        this.movieDaoImpl = MovieDaoImpl.getInstance();
        this.createMovieMapper = CreateMovieMapper.getInstance();
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }

    public List<MovieDto> findAll() {
        return movieDaoImpl.findAll().stream()
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
        return movieDaoImpl.findById(id)
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getName(),
                        movie.getGenre(),
                        movie.getDescription(),
                        movie.getPoster_url(),
                        movie.getRelease_date()
                ));
    }
    public void addMovie(CreateMovieDto movieDto) {
        var movieEntity = createMovieMapper.mapFrom(movieDto);
        movieDaoImpl.save(movieEntity);
    }

    public boolean deleteMovie(Integer id) {
        return movieDaoImpl.deleteById(id);
    }
    public void moveToWatched(int movieId) {
        // 1. Найти запись в Watchlist с данным movieId и типом "watching"
        WatchlistDaoImpl watchlistDao = new WatchlistDaoImpl(HibernateUtil.getSessionFactory());
        Watchlist watchlistItem = watchlistDao.findByMovieIdAndListType(movieId, "watching");

        if (watchlistItem != null) {
            // 2. Обновить список с "watching" на "watched"
            watchlistItem.setListType("watched");
            watchlistDao.update(watchlistItem); // Обновляем запись в базе
        } else {
            throw new IllegalArgumentException("Фильм не найден в списке 'буду смотреть'");
        }
    }
}
