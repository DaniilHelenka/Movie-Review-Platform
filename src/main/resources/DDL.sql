CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR NOT NULL

);

CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        genre VARCHAR(100),
                        description TEXT,
                        poster VARCHAR(255),
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
                           id SERIAL PRIMARY KEY,
                           user_id INT NOT NULL,
                           movie_id INT NOT NULL,
                           list_type VARCHAR(10) CHECK (list_type IN ('watching', 'watched')),
                           UNIQUE (user_id, movie_id, list_type),
                           FOREIGN KEY (user_id) REFERENCES users (id),
                           FOREIGN KEY (movie_id) REFERENCES movies (id)
);
INSERT INTO movies (name, genre, description, poster, release_date)
VALUES ('Example Movie3', 'Drama', 'An example description3', '3', '2024-11-28');

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
