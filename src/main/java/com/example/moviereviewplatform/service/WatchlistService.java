package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.WatchlistDao;
import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.mapper.WatchlistMapper;

import java.util.List;
import java.util.stream.Collectors;

public class WatchlistService {
    private static final WatchlistService INSTANCE = new WatchlistService();
    private final WatchlistDao watchlistDao = WatchlistDao.getInstance();

    public void addMovieToWatchlist(int userId, int movieId, String listType) {
        watchlistDao.addToWatchlist(userId, movieId, listType);
    }

    public void removeMovieFromWatchlist(int userId, int movieId, String listType) {
        watchlistDao.removeFromWatchlist(userId, movieId, listType);
    }
    public List<WatchlistDto> getUserWatchlistByType(int userId, String listType) {
        List<Watchlist> watchlists = watchlistDao.findByUser(userId);

        return watchlists.stream()
                .filter(w -> w.getListType().equalsIgnoreCase(listType))
                .map(w -> WatchlistMapper.getInstance().mapFrom(w)) // Это будет уже DTO с названием фильма
                .collect(Collectors.toList());
    }

    public List<WatchlistDto> getUserWatchlist(int userId) {
        List<Watchlist> watchlists = watchlistDao.findByUser(userId);

                 return watchlists.stream()
                .map(w -> WatchlistMapper.getInstance().mapFrom(w)) // Получаем экземпляр маппера и применяем mapFrom
                .collect(Collectors.toList());
    }

    public static WatchlistService getInstance() {
        return INSTANCE;
    }
}