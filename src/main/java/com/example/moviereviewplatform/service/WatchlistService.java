package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.MovieDao;
import com.example.moviereviewplatform.dao.WatchlistDao;
import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.mapper.WatchlistMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WatchlistService {

    private static final WatchlistService INSTANCE = new WatchlistService();
    private final MovieDao movieDao;
    private final WatchlistDao watchlistDao;

    private WatchlistService() {
    this.movieDao = MovieDao.getInstance();
    this.watchlistDao = WatchlistDao.getInstance();
    }


    public static WatchlistService getInstance() {
        return INSTANCE;
    }


    public void addMovieToWatchlist(int userId, Integer movieId, String listType) {
        Optional<Movies> movieOptional = movieDao.findById(movieId);
        if (!movieOptional.isPresent()) {
            throw new RuntimeException("Movie not found with ID: " + movieId);
        }

        Movies movie = movieOptional.get();
        Watchlist watchlist = Watchlist.builder()
                .userId(userId)
                .movie(movie)
                .listType(listType)
                .build();

        try (Session session = watchlistDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(watchlist);  // Сохраняем watchlist
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при добавлении фильма в список", e);
        }
    }

    public boolean removeMovieFromWatchlist(int userId, int movie_id, String listType) {
        Watchlist watchlist = watchlistDao.findByMovieIdAndListType(movie_id, listType);
        if (watchlist != null && watchlist.getUserId() == userId) {
            return watchlistDao.deleteById(watchlist.getId());
        }
        return false;
    }
    public List<WatchlistDto> getUserWatchlistByType(int userId, String listType) {
        List<Watchlist> watchlists = watchlistDao.findByUserId(userId);

        return watchlists.stream()
                .filter(w -> w.getListType().equalsIgnoreCase(listType))
                .map(w -> WatchlistMapper.getInstance().mapFrom(w)) // Это будет уже DTO с названием фильма
                .collect(Collectors.toList());
    }

    public List<WatchlistDto> getUserWatchlist(int userId) {
        List<Watchlist> watchlists = watchlistDao.findByUserId(userId);
        return watchlists.stream()
                .map(WatchlistMapper.getInstance()::mapFrom)
                .collect(Collectors.toList());
    }
}
