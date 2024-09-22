package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.WishListItem;

/**
 * WishListItemRepository.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Sep-24
 */

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
    // Additional query methods if needed
}
