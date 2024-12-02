package com.example.moviereviewplatform.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String genre;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String poster_url;
    @Column(name = "release_date")
    private LocalDateTime release_date;

    public Movies(Integer id, String name,String genre, String description, String poster_url) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.poster_url = poster_url;
    }

    public Movies() {

    }

    public LocalDateTime getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDateTime release_date) {
        this.release_date = release_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(id, movies.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movies{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", genre='" + genre + '\'' +
               ", description='" + description + '\'' +
               ", poster_url='" + poster_url + '\'' +
               '}';
    }
}
