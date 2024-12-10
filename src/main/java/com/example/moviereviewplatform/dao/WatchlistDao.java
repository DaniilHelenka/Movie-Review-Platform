package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDao {
    private static final WatchlistDao INSTANCE = new WatchlistDao();

    private static final String ADD_TO_WATCHLIST = """
        INSERT INTO watchlist (user_id, movie_id, list_type) VALUES (?, ?, ?)
        ON CONFLICT (user_id, movie_id, list_type) DO NOTHING
    """;

    private static final String REMOVE_FROM_WATCHLIST = """
        DELETE FROM watchlist WHERE user_id = ? AND movie_id = ? AND list_type = ?
    """;

    private static final String FIND_BY_USER_AND_LIST_TYPE = """
        SELECT w.id, w.user_id, w.movie_id, w.list_type, m.name AS movie_name
        FROM watchlist w
        JOIN movies m ON w.movie_id = m.id
        WHERE w.user_id = ? AND w.list_type = ?
    """;

    private WatchlistDao() {}

    public void addToWatchlist(int userId, int movieId, String listType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(ADD_TO_WATCHLIST)) {
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
             var preparedStatement = connection.prepareStatement(REMOVE_FROM_WATCHLIST)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, movieId);
            preparedStatement.setString(3, listType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Watchlist> findByUserAndListType(int userId, String listType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_USER_AND_LIST_TYPE)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, listType);
            var resultSet = preparedStatement.executeQuery();
            List<Watchlist> watchlist = new ArrayList<>();
            while (resultSet.next()) {
                watchlist.add(new Watchlist(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("movie_id"),
                        resultSet.getString("list_type"),
                        resultSet.getString("movie_name")
                ));
            }
            return watchlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WatchlistDao getInstance() {
        return INSTANCE;
    }
}