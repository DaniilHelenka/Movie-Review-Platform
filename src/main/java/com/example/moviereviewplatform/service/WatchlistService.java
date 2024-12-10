package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.WatchlistDao;
import com.example.moviereviewplatform.entity.Watchlist;

import java.util.List;

public class WatchlistService {
    private static final WatchlistService INSTANCE = new WatchlistService();
    private final WatchlistDao watchlistDao = WatchlistDao.getInstance();

    public void addMovieToWatchlist(int userId, int movieId, String listType) {
        watchlistDao.addToWatchlist(userId, movieId, listType);
    }

    public void removeMovieFromWatchlist(int userId, int movieId, String listType) {
        watchlistDao.removeFromWatchlist(userId, movieId, listType);
    }

    public List<Watchlist> getUserWatchlistByType(int userId, String listType) {
        return watchlistDao.findByUserAndListType(userId, listType);
    }

    public static WatchlistService getInstance() {
        return INSTANCE;
    }
}