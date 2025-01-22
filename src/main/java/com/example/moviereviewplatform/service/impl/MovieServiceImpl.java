package com.example.moviereviewplatform.service.impl;

import com.example.moviereviewplatform.dao.impl.MovieDaoImpl;
import com.example.moviereviewplatform.dao.impl.WatchlistDaoImpl;
import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.mapper.CreateMovieMapper;
import com.example.moviereviewplatform.mapper.MovieMapper;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    private static final MovieServiceImpl INSTANCE = new MovieServiceImpl();

    private final MovieDaoImpl movieDaoImpl;
    private final CreateMovieMapper createMovieMapper;
    private final MovieMapper movieMapper;

    private MovieServiceImpl() {
        this.movieDaoImpl = MovieDaoImpl.getInstance();
        this.createMovieMapper = CreateMovieMapper.getInstance();
        this.movieMapper = MovieMapper.getInstance();
    }

    public static MovieServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<MovieDto> findAll() {
        return movieDaoImpl.findAll().stream()
                .map(movieMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findAllPaginated(int page, int size) {
        return movieDaoImpl.findAllPaginated(page, size).stream()
                .map(movieMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> findById(Integer id) {
        return movieDaoImpl.findById(id)
                .map(movieMapper::mapFrom);
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
