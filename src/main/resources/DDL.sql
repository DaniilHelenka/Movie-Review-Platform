CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role_id INT NOT NULL,
                       FOREIGN KEY (role_id) REFERENCES roles (id)
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