package com.example.moviereviewplatform.dto;

import java.util.Objects;

public class MovieDto {
    private  Integer id;
    private  String name;
    private  String description;
    private   String poster_url;


    public MovieDto(Integer id, String name, String description, String poster_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.poster_url = poster_url;
    }

    public String getDescription() {
        return description;
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
               '}';
    }
}
