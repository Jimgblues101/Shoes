package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CartService.java
 *
 * Service implementation for managing Cart entities.
 * Implements ICartService to provide basic CRUD operations and additional query methods.
 *
 * @autor Rethabile Ntsekhe
 * @date 25-Aug-24
 */

@Service
@Transactional
public class CartService implements ICartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Creates a new Cart.
     *
     * @param cart the Cart entity to be created
     * @return the created Cart
     */
    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * Reads a Cart by its ID.
     *
     * @param id the ID of the Cart to be read
     * @return the Cart entity if found, or null if not found
     */
    @Override
    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }


    /**
     * Updates an existing Cart.
     *
     * @param cartDetails the Cart entity to be updated
     * @return the updated Cart entity, or null if the Cart does not exist
     */
    @Override
       public Cart update(Cart cartDetails) {
        if(cartDetails.getId() == null || !cartRepository.existsById(cartDetails.getId())){
            throw new IllegalArgumentException("Cart with the given ID does not exist.");
        }
        Cart existingCartItem = cartRepository.findById(cartDetails.getId()).orElseThrow();
        Cart updatedCart = CartFactory.createCart(
                existingCartItem.getId(),
                cartDetails.getUser(),
                cartDetails.getTotal(),
               cartDetails.getCreatedAt(),
                cartDetails.getUpdatedAt()
        );
        return cartRepository.save(updatedCart);
    }

    /**
     * Deletes a Cart by its ID.
     *
     * @param id the ID of the Cart to be deleted
     */
    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    /**
     * Finds all Carts in the database.
     *
     * @return a list of all Cart entities
     */
    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    /**
     * Finds all Carts associated with a specific user ID.
     *
     * @param userId the user ID to search by
     * @return a list of Carts associated with the given user ID
     */
    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * Finds all Carts created after a specific date.
     *
     * @param createdAt the date to search by
     * @return a list of Carts created after the given date
     */
    @Override
    public List<Cart> findByCreatedAtAfter(LocalDateTime createdAt) {
        return cartRepository.findByCreatedAtAfter(createdAt);
    }

    /**
     * Finds all Carts with a total greater than a specified amount.
     *
     * @param total the minimum total value to search by
     * @return a list of Carts with a total greater than the specified amount
     */
    @Override
    public List<Cart> findByTotalGreaterThan(Double total) {
        return cartRepository.findByTotalGreaterThan(total);
    }

    /**
     * Finds all Carts that were updated after a certain date.
     *
     * @param updatedAt the date to search by
     * @return a list of Carts updated after the given date
     */
    @Override
    public List<Cart> findByUpdatedAtAfter(LocalDateTime updatedAt) {
        return cartRepository.findByUpdatedAtAfter(updatedAt);
    }

    /**
     * Finds all Carts created within a specific date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a list of Carts created within the date range
     */
    @Override
    public List<Cart> findCartsCreatedWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return cartRepository.findCartsCreatedWithinDateRange(startDate, endDate);
    }

    /**
     * Finds the Cart with the highest total.
     *
     * @return the Cart with the highest total
     */
    @Override
    public Cart findCartWithHighestTotal() {
        return cartRepository.findCartWithHighestTotal();
    }

    /**
     * Finds all Carts with a total greater than a specified amount using a native query.
     *
     * @param total the minimum total value to search by
     * @return a list of Carts with a total greater than the specified amount
     */
    @Override
    public List<Cart> findCartsWithTotalGreaterThan(Double total) {
        return cartRepository.findCartsWithTotalGreaterThan(total);
    }

    /**
     * Finds all Carts associated with a specific user ID and created after a specific date.
     *
     * @param userId the user ID to search by
     * @param createdAt the date to search by
     * @return a list of Carts associated with the given user ID and created after the given date
     */
    @Override
    public List<Cart> findByUserIdAndCreatedAtAfter(Long userId, LocalDateTime createdAt) {
        return cartRepository.findByUserIdAndCreatedAtAfter(userId, createdAt);
    }

    /**
     * Finds all Carts associated with a specific user ID and updated after a specific date.
     *
     * @param userId the user ID to search by
     * @param updatedAt the date to search by
     * @return a list of Carts associated with the given user ID and updated after the given date
     */
    @Override
    public List<Cart> findByUserIdAndUpdatedAtAfter(Long userId, LocalDateTime updatedAt) {
        return cartRepository.findByUserIdAndUpdatedAtAfter(userId, updatedAt);
    }

    /**
     * Finds all Carts created before a specific date.
     *
     * @param createdAt the date to search by
     * @return a list of Carts created before the given date
     */
    @Override
    public List<Cart> findByCreatedAtBefore(LocalDateTime createdAt) {
        return cartRepository.findByCreatedAtBefore(createdAt);
    }

    /**
     * Finds all Carts updated before a specific date.
     *
     * @param updatedAt the date to search by
     * @return a list of Carts updated before the given date
     */
    @Override
    public List<Cart> findByUpdatedAtBefore(LocalDateTime updatedAt) {
        return cartRepository.findByUpdatedAtBefore(updatedAt);
    }

    /**
     * Finds all Carts created in the last 30 days.
     *
     * @return a list of Carts created in the last 30 days
     */
    @Override
    public List<Cart> findCartsCreatedInLast30Days() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return cartRepository.findByCreatedAtAfter(thirtyDaysAgo);
    }

    /**
     * Deletes all Carts associated with a specific user ID.
     *
     * @param userId the user ID of the Carts to be deleted
     */
    @Override
    public void deleteByUserId(Long userId) {
        List<Cart> userCarts = cartRepository.findByUserId(userId);
        for (Cart cart : userCarts) {
            cartRepository.delete(cart);
        }
    }
}
