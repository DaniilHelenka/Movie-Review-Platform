package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.util.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
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

    public WatchlistDto findByUserAndMovieAndType(Integer userId, Integer movieId, String listType) {
        // Пример поиска записи в базе данных по userId, movieId и listType
      try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement("SELECT * FROM watchlist WHERE user_id = ? AND movie_id = ? AND list_type = ?"))  {
          preparedStatement.setInt(1, userId);
          preparedStatement.setInt(2, movieId);
          preparedStatement.setString(3, listType);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Если запись найдена, создаем объект Watchlist
                    return new WatchlistDto (
                            resultSet.getInt("id"),
                            resultSet.getString("user_id"),
                            resultSet.getString("list_type"),
                            resultSet.getInt("movie_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Если записи нет
    }
    public boolean delete(Integer user_id, Integer movie_id, String list_type) {
        String sql = "DELETE FROM watchlist WHERE user_id = ? AND movie_id = ? AND list_type = ?";

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql)) {

            // Устанавливаем параметры для запроса (user_id, movie_id и list_type)
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, movie_id);
            preparedStatement.setString(3, list_type);

            // Выполняем запрос
            int rowsAffected = preparedStatement.executeUpdate();

            // Проверяем, что запись была удалена
            if (rowsAffected == 0) {
                throw new SQLException("No rows affected. Deletion failed.");
            }
        } catch (SQLException e) {
            // Логируем исключение
            e.printStackTrace();
            throw new RuntimeException("Error deleting movie from watchlist", e);
        }
        return false;
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