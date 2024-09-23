package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderItem;

/**
 * OrderItemRepository.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
   void deleteOrderItemByOrderDetails_Id(Long orderDetailsId);


}
