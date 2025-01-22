package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.entity.Watchlist;

public class WatchlistMapper {

    private static final WatchlistMapper INSTANCE = new WatchlistMapper();

    private WatchlistMapper() {
    }

    public static WatchlistMapper getInstance() {
        return INSTANCE;
    }

    public WatchlistDto mapFrom(Watchlist watchlist) {
        return WatchlistDto.builder()
                .id(watchlist.getId())
                .movieId(watchlist.getMovie().getId())
                .movieName(watchlist.getMovie().getName())
                .listType(watchlist.getListType())
                .build();
    }
}
