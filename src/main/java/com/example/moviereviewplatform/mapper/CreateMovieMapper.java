package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Role;
import com.example.moviereviewplatform.entity.User;

public class CreateMovieMapper implements Mapper<MovieDto, Movies> {
    @Override
    public Movies mapFrom(MovieDto object) {
        return null;
    }
/*
    private static final String IMAGE_FOLDER2 = "user/";

    private static final CreateMovieMapper INSTANCE = new CreateMovieMapper();
    @Override
    public Movies mapFrom(MovieDto object) {
        return Movies.builder()
                .name(object.getName())
                .poster_url(IMAGE_FOLDER2 + object.getPoster_url().getSubmittedFileName())
                .genre(object.getGenre())
                .description(object.getDescription())
                .build();
    }

    public static CreateMovieMapper getInstance() {
        return INSTANCE;
    }*/
}
