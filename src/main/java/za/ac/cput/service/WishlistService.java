package za.ac.cput.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.WishListItem;
import za.ac.cput.domain.Wishlist;
import za.ac.cput.repository.WishlistRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WishlistService.java
 *
 * This service handles operations for managing Wishlist entities.
 * It includes methods for creating, reading, updating, and deleting Wishlists.
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */
@Service
@Transactional
public class WishlistService implements iWishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Create a new wishlist.
     *
     * @param wishlist the wishlist to create
     * @return the saved wishlist
     */
    @Override
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    /**
     * Read and retrieve a wishlist by its ID.
     * Lazily loads WishlistItems and their related Products to avoid LazyInitializationException.
     *
     * @param id the ID of the wishlist to retrieve
     * @return the found wishlist
     * @throws EntityNotFoundException if the wishlist is not found
     */
    @Override
    @Transactional(readOnly = true) // Mark this read-only as it doesn't modify the database
    public Wishlist read(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));

        // Access to initialize lazy-loaded collections (WishlistItems and their Products' SubCategories)
        for (WishListItem item : wishlist.getWishListItems()) {
            item.getProduct().getSubCategory().size(); // Ensure subCategory is initialized
        }
        return wishlist;
    }

    /**
     * Update an existing wishlist.
     *
     * @param wishlist the wishlist with updated data
     * @return the updated wishlist
     * @throws IllegalArgumentException if the wishlist with the provided ID does not exist
     */
    @Override
    public Wishlist update(Wishlist wishlist) {
        // Check if the wishlist exists in the repository
        Wishlist existingWishlist = wishlistRepository.findById(wishlist.getId()).orElse(null);
        if (existingWishlist != null) {
            // Use the Builder pattern to create an updated version of the wishlist
            Wishlist updatedWishlist = new Wishlist.Builder()
                    .copy(existingWishlist)
                    .setUser(wishlist.getUser())
                    .setWishlistItems(wishlist.getWishListItems())
                    .build();
            return wishlistRepository.save(updatedWishlist);
        } else {
            throw new IllegalArgumentException("Wishlist with ID " + wishlist.getId() + " does not exist");
        }
    }

    /**
     * Delete a wishlist by its ID.
     *
     * @param id the ID of the wishlist to delete
     */
    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id); // Remove the wishlist by its ID
    }

    /**
     * Retrieve all wishlists.
     *
     * @return a list of all wishlists
     */
    @Override
    @Transactional(readOnly = true)
    public List<Wishlist> findAll() {
        // this method will now ignore rows where deleted at is not null
        return wishlistRepository.findAll().stream()
                .filter(wishlist -> wishlist.getDeletedAt() == null)
                .collect(Collectors.toList());
    }
}
