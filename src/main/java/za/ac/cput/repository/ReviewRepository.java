package za.ac.cput.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

import java.util.List;

/**
 * ReviewRepository.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Sep-24
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct_Id(Long id);

    List<Review> findByRating(int rating);

    List<Review> findByRatingGreaterThan(int rating);

}
