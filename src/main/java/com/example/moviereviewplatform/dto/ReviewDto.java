package com.example.moviereviewplatform.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
@Builder
public class ReviewDto {
    Integer id;
    Integer movieId;
    Integer rating;
    String comments;
    LocalDateTime created_at;

    public ReviewDto(int id, int movieId, int rating, String comments, LocalDateTime created_at) {
        this.id = id;
        this.rating = rating;
        this.comments = comments;
        this.created_at = created_at;
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDto reviewDto = (ReviewDto) o;
        return id == reviewDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
               "id=" + id +
               ", movieId=" + movieId +
               ", rating=" + rating +
               ", comments='" + comments + '\'' +
               ", created_at=" + created_at +
               '}';
    }
}
