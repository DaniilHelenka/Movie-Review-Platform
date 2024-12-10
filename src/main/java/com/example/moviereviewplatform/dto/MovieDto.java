package com.example.moviereviewplatform.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class MovieDto {
    Integer id;
    String name;
    String genre;
    String description;
    String   poster_url;
    LocalDate release_date;

    public MovieDto(Integer id, String name, String genre, String description, String poster_url, LocalDate release_date) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.poster_url = poster_url;
        this.release_date = release_date;
    }
}
