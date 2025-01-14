package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.ReviewDao;
import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.util.HibernateUtil;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReviewService {
    private static final ReviewService INSTANCE = new ReviewService();
    private final ReviewDao reviewDao;
    private ReviewService(){
        this.reviewDao = new ReviewDao(HibernateUtil.getSessionFactory());
    }

    public void addReview(Integer user_id, Integer movie_id, Integer rating, String comments, LocalDateTime created_at) {
        Reviews reviews = new Reviews();

        reviews.setUserId(user_id);
        reviews.setMovieId(movie_id);
        reviews.setRating(rating);
        reviews.setComments(comments);
        reviews.setCreatedAt(created_at);

        reviewDao.save(reviews);
    }
    

    public List<ReviewDto> findAll(int movieId){
        return reviewDao.findAll().stream()
                .filter(reviews -> reviews.getMovieId() == movieId)
                .map(reviews -> new ReviewDto(
                        reviews.getUserId(),
                        reviews.getMovieId(),
                        reviews.getRating(),
                        reviews.getComments(),
                        reviews.getCreatedAt()
                ))
                .collect(toList());
    }

    public static ReviewService getInstance(){
        return INSTANCE;
    }
}
