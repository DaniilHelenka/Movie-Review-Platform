package com.example.moviereviewplatform.service;

import antlr.PreservingFileWriter;
import com.example.moviereviewplatform.dao.ReviewDao;
import com.example.moviereviewplatform.dto.ReviewDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ReviewService {
    private static final ReviewService INSTANCE = new ReviewService();
    private final ReviewDao reviewDao = ReviewDao.getInstance();
    private ReviewService(){
    }

    public List<ReviewDto> findById(Integer movieId){
        return reviewDao.findById(movieId).stream()
                .map(review -> new ReviewDto(
                        review.getId(),
                        review.getRating(),
                        review.getMovieId(),
                        review.getComment(),
                        review.getCreatedAt()
                ))
                .collect(toList());
    }
    public static ReviewService getInstance(){
        return INSTANCE;
    }

}
