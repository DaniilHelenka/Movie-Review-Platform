package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDao {

    private static final WatchlistDao INSTANCE = new WatchlistDao();

    private static final String INSERT_WATCHLIST = """
        INSERT INTO watchlist (user_id, movie_id, list_type)
        VALUES (?, ?, ?)
        ON CONFLICT DO NOTHING
    """;

    private static final String DELETE_WATCHLIST = """
        DELETE FROM watchlist WHERE user_id = ? AND movie_id = ? AND list_type = ?
    """;

    private static final String FIND_BY_USER = """
        SELECT w.id, w.movie_id, w.list_type, m.name AS movie_name
        FROM watchlist w
        JOIN movies m ON w.movie_id = m.id
        WHERE w.user_id = ?
    """;

    private WatchlistDao() {}

    public void addToWatchlist(int userId, int movieId, String listType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(INSERT_WATCHLIST)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, movieId);
            preparedStatement.setString(3, listType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFromWatchlist(int userId, int movieId, String listType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_WATCHLIST)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, movieId);
            preparedStatement.setString(3, listType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Watchlist> findByUser(int userId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_USER)) {
            preparedStatement.setInt(1, userId);
            var resultSet = preparedStatement.executeQuery();
            List<Watchlist> watchlists = new ArrayList<>();
            while (resultSet.next()) {
                watchlists.add(new Watchlist(
                        resultSet.getInt("id"),
                        userId,
                        resultSet.getInt("movie_id"),
                        resultSet.getString("list_type")
                ));
            }
            return watchlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WatchlistDao getInstance() {
        return INSTANCE;
    }
}