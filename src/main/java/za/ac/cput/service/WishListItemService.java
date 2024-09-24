package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.WishListItem;
import za.ac.cput.repository.WishListItemRepository;

import java.util.List;

/**
 * WishListItemService.java
 *
 * Service class for managing WishListItem operations.
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Sep-24
 */
@Service
@Transactional
public class WishListItemService implements IWishListItems {

    @Autowired
    private final WishListItemRepository repository;

    @Autowired
    public WishListItemService(WishListItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public WishListItem create(WishListItem wishListItem) {
        return repository.save(wishListItem);
    }

    @Override
    public WishListItem read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public WishListItem update(WishListItem wishListItem) {
        WishListItem existingWishListItem = repository.findById(wishListItem.getId()).orElse(null);

        if (existingWishListItem != null) {
            // Build the updated item
            WishListItem updatedItem = new WishListItem.Builder()
                    .copy(wishListItem)
                    .setId(existingWishListItem.getId()) // Preserve the ID
                    .setProduct(wishListItem.getProduct())  // Update other fields
                    .setDateAdded(wishListItem.getDateAdded())
                    .setWishlist(wishListItem.getWishlist())
                    .build();

            return repository.save(updatedItem); // Save the updated item
        } else {
            throw new IllegalArgumentException("Wishlist with ID " + wishListItem.getId() + " does not exist");
        }
    }

    @Override
    public List<WishListItem> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);

        // Check if the entity still exists after deletion
        boolean exists = repository.existsById(id);

        // Return false if entity was deleted successfully, otherwise return true
        return !exists;
    }

    @Override
    public void deleteByWishlistId(Long wishlistId) {
        repository.deleteByWishlistId(wishlistId);

        boolean exists = repository.existsById(wishlistId);

    }

}
