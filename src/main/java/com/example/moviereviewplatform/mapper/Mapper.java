package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.entity.Movies;

public interface Mapper <F, T>{


    T mapFrom(F object);

}
