package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CartItem;

import java.util.List;

/**
 * CartItemRepository.java
 *
 * Repository interface for accessing CartItem entities from the database.
 * Extends JpaRepository to provide basic CRUD operations.
 * Custom query methods are defined to find CartItems by specific fields.
 *
 * Author: Rethabile Ntsekhe
 * Student Number: 220455430
 * Date: 25-Aug-24
 */

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Finds CartItems by their associated Cart ID.
     *
     * @param cartId the ID of the Cart entity to search by
     * @return a list of CartItems associated with the given Cart ID
     */
    List<CartItem> findByCartId(Long cartId);

    /**
     * Finds CartItems by their associated Product ID.
     *
     * @param productId the ID of the Product entity to search by
     * @return a list of CartItems associated with the given Product ID
     */
    List<CartItem> findByProductId(Long productId);

    /**
     * Finds CartItems by their associated Product SKU ID.
     *
     * @param productSkuId the ID of the ProductSkuService entity to search by
     * @return a list of CartItems associated with the given Product SKU ID
     */
    List<CartItem> findByProductSkuId(Long productSkuId);

    /**
     * Finds CartItems by their quantity.
     *
     * @param quantity the quantity of items to search by
     * @return a list of CartItems with the given quantity
     */
    List<CartItem> findByQuantity(int quantity);

    /**
     * Finds CartItems by their associated Cart ID and Product ID.
     *
     * @param cartId the ID of the Cart entity to search by
     * @param productId the ID of the Product entity to search by
     * @return a list of CartItems associated with the given Cart ID and Product ID
     */
    List<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    /**
     * Finds CartItems by their associated Cart ID and Product SKU ID.
     *
     * @param cartId the ID of the Cart entity to search by
     * @param productSkuId the ID of the ProductSkuService entity to search by
     * @return a list of CartItems associated with the given Cart ID and Product SKU ID
     */
    List<CartItem> findByCartIdAndProductSkuId(Long cartId, Long productSkuId);

    /**
     * Deletes CartItems by their associated Cart ID.
     *
     * @param cartId the ID of the Cart entity to search by
     */
    void deleteByCartId(Long cartId);
}
