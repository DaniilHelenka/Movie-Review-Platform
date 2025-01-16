package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Watchlist;

import java.util.List;

public interface WatchlistDao extends Dao<Integer, Watchlist> {
    List<Watchlist> findByUserId(int userId);
    Watchlist findByMovieIdAndListType(int movieId, String listType);
    void update(Watchlist watchlist);
}
