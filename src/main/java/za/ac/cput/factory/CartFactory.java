package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Factory class for creating instances of {@link Cart}.
 * Provides static methods to create {@link Cart} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CartFactory {

    /**
     * Creates a {@link Cart} instance from various inputs.
     *
     * @param id         the ID of the cart
     * @param user       the {@link User} entity associated with this cart
     * @param total      the total cost of the cart
     * @param createdAt  the date the cart was created
     * @param updatedAt  the date the cart was updated (if applicable)
     * @return a new {@link Cart} object with properties set from the input parameters
     */
    public static Cart createCart(Long id, User user, Double total, LocalDateTime createdAt, LocalDateTime updatedAt) {

        if (Helper.isNullOrEmpty(user) ||
            Helper.isDoubleNullOrEmpty(total)
        ) {

            throw new IllegalArgumentException("User cannot be null cart Factory can't be null");
        }

        return new Cart.Builder()
                .setId(id)
                .setUser(user)
                .setTotal(total)
                .setCreatedAt(createdAt)
                .setUpdatedAt(LocalDateTime.now())
                .build();
    }
}