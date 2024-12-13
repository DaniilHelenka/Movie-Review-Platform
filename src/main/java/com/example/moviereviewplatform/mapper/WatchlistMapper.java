package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.service.MovieService;

public class WatchlistMapper implements Mapper<Watchlist, WatchlistDto> {
    private static final WatchlistMapper INSTANCE = new WatchlistMapper();
    private final MovieService movieService = new MovieService();

    @Override
    public WatchlistDto mapFrom(Watchlist watchlist) {
        // Получаем название фильма по ID
        String movieName = movieService.findById(watchlist.getMovieId())
                .map(movieDto -> movieDto.getName())  // Если фильм найден, берем его название
                .orElse("Unknown Movie");  // Если фильм не найден, возвращаем "Unknown Movie"

        return WatchlistDto.builder()
                .id(watchlist.getId())
                .movieName(movieName)
                .listType(watchlist.getListType())
                .movieId(watchlist.getMovieId())
                .build();
    }

    public static WatchlistMapper getInstance() {
        return INSTANCE;
    }
}