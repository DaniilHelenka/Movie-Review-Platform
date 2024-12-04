package com.example.moviereviewplatform.dto;

import java.time.LocalDate;
import java.util.Objects;

public class MovieDto {
    private  Integer id;
    private  String name;
    private String genre;
    private  String description;
    private   String poster_url;
    private LocalDate release_date;


    public MovieDto(Integer id, String name, String genre, String description, String poster_url) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.poster_url = poster_url;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public  String getPoster_url(){
        return poster_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(id, movieDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MovieDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", genre='" + genre + '\'' +
               ", description='" + description + '\'' +
               ", poster_url='" + poster_url + '\'' +
               '}';
    }
}
