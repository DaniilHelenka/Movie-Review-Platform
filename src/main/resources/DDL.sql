CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       image VARCHAR(124) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR NOT NULL

);

CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        genre VARCHAR(100),
                        description TEXT,
                        release_date DATE
);

CREATE TABLE reviews (
                          id SERIAL PRIMARY KEY,
                          user_id INT NOT NULL,
                          movie_id INT NOT NULL,
                          rating INT CHECK (rating BETWEEN 1 AND 10),
                          comment TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users (id),
                          FOREIGN KEY (movie_id) REFERENCES movies (id)
);

CREATE TABLE watchlist (
                           id SERIAL PRIMARY KEY,                  -- Уникальный идентификатор записи
                           user_id INT NOT NULL,                   -- ID пользователя
                           movie_id INT NOT NULL,                  -- ID фильма
                           list_type VARCHAR(50) NOT NULL,         -- Тип списка: "watching" или "watched"
                           CONSTRAINT uc_user_movie UNIQUE (user_id, movie_id, list_type), -- Уникальность комбинации user_id, movie_id и list_type
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                           CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE
);

INSERT INTO movies (name, genre, description,  release_date)
VALUES ('Example Movie3', 'Drama', 'An example description3', '2024-11-28');

CREATE TABLE posters (
                         id SERIAL PRIMARY KEY,
                         movie_id INT NOT NULL,
                         poster_url VARCHAR(255) NOT NULL,
                         FOREIGN KEY (movie_id) REFERENCES movies(id)
);

INSERT INTO posters (movie_id, poster_url)
VALUES (1, 'http://localhost:8080/MovieReviewPlatform_war_exploded/posters/alien.jpg'),
       (2, 'http://localhost:8080/MovieReviewPlatform_war_exploded/posters/hancock.jpg'),
       (3, 'http://localhost:8080/MovieReviewPlatform_war_exploded/posters/legenda.jpg');
