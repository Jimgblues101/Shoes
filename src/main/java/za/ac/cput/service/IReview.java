package za.ac.cput.service;

import za.ac.cput.domain.Review;

import java.util.List;

/**
 * IReview.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Sep-24
 */

public interface IReview extends IService<Review, Long> {
    List<Review> findByProduct_Id(Long id);
    List<Review> findByRating(int rating);
    List<Review> findByRatingGreaterThan(int rating);
}
