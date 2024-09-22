package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.WishListItem;
import za.ac.cput.domain.Wishlist;

import java.time.LocalDateTime;

/**
 * Factory class for creating instances of {@link WishListItem}.
 * Provides static methods to create {@link WishListItem} objects from various inputs.
 *
 * Author: Rethabile Ntsekhe
 * Date: 20-Sep-24
 */
public class WishlistItemFactory {

    /**
     * Creates a {@link WishListItem} instance with the necessary parameters.
     *
     * @param product    the {@link Product} associated with this wishlist item
     * @param wishlist   the {@link Wishlist} this item belongs to
     * @param dateAdded  the date when the item was added to the wishlist
     * @return a new {@link WishListItem} object with properties set from the input parameters
     */
    public static WishListItem createWishlistItem(Product product, Wishlist wishlist, LocalDateTime dateAdded) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null in WishlistItemFactory");
        }

        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist cannot be null in WishlistItemFactory");
        }

        // Set default value if dateAdded is null
        if (dateAdded == null) {
            dateAdded = LocalDateTime.now();
        }

        return new WishListItem.Builder()
                .setProduct(product)
                .setWishlist(wishlist)
                .setDateAdded(dateAdded)
                .build();
    }
}
