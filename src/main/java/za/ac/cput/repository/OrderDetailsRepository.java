package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderDetails;


import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    /**
     * Finds all orders by a specific user ID.
     *
     * @param userId the user ID to search by.
     * @return a list of OrderDetails for the specified user.
     */
    List<OrderDetails> findByUserId(Long userId);

    /**
     * Finds all orders with a total greater than or equal to a specified amount.
     *
     * @param total the minimum total amount to search by.
     * @return a list of OrderDetails with a total greater than or equal to the specified amount.
     */
    List<OrderDetails> findByTotalGreaterThanEqual(Double total);

    /**
     * Finds all orders created after a specified date.
     *
     * @param createdAt the date to search by.
     * @return a list of OrderDetails created after the specified date.
     */
    List<OrderDetails> findByCreatedAtAfter(LocalDateTime createdAt);

    /**
     * Finds all orders updated before a specified date.
     *
     * @param updatedAt the date to search by.
     * @return a list of OrderDetails updated before the specified date.
     */
    List<OrderDetails> findByUpdatedAtBefore(LocalDateTime updatedAt);

    /**
     * Custom query to find orders by payment ID.
     *
     * @param paymentId the payment ID to search by.
     * @return a list of OrderDetails with the specified payment ID.
     */

    List<OrderDetails> findByPaymentDetails_Id(@Param("paymentId") Long paymentId);

    /**
     * Finds all orders by a specific user ID and orders them by creation date descending.
     *
     * @param userId the user ID to search by.
     * @return a list of OrderDetails for the specified user, ordered by creation date descending.
     */
    List<OrderDetails> findByUserIdOrderByCreatedAtDesc(Long userId);
}
