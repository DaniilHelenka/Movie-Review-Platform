package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MovieMapper implements Mapper<Movies, MovieDto>{

    private static final MovieMapper INSTANCE = new MovieMapper();


    @Override
    public MovieDto mapFrom(Movies object) {
        return MovieDto.builder()
                .id(object.getId())
                .name(object.getName())
                .genre(object.getGenre())
                .description(object.getDescription())
                .release_date(object.getRelease_date())
                .poster_url(object.getPoster_url())
                .build();
    }

    public static MovieMapper getInstance() {
        return INSTANCE;
    }
}
