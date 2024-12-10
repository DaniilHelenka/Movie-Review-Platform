package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Movies;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMovieMapper implements Mapper<CreateMovieDto, Movies> {

    private static final String IMAGE_FOLDER = "posters/";
    private static final CreateMovieMapper INSTANCE = new CreateMovieMapper();

    @Override
    public Movies mapFrom(CreateMovieDto object) {
        return Movies.builder()
                .name(object.getName())
                .genre(object.getGenre())
                .description(object.getDescription())
                .release_date(object.getRelease_date())
                .poster_url(IMAGE_FOLDER + object.getPoster_url().getSubmittedFileName())
                .build();
    }
    public static CreateMovieMapper getInstance() {
        return INSTANCE;
    }


}
