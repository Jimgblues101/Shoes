package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Wishlist;
import za.ac.cput.service.WishlistService;

import java.util.List;

/**
 * WishlistController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    /**
     * Creates a new wishlist item.
     *
     * @param wishlist the wishlist item to be created
     * @return ResponseEntity containing the created Wishlist and HTTP status code
     */
    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.create(wishlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWishlist);
    }

    /**
     * Retrieves a wishlist item by its ID.
     *
     * @param id the ID of the wishlist item to retrieve
     * @return ResponseEntity containing the Wishlist if found, or a 404 Not Found status if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.read(id);
        if (wishlist != null) {
            return ResponseEntity.ok(wishlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing wishlist item.
     *
     * @param id the ID of the wishlist item to be updated
     * @param wishlist the updated wishlist item details
     * @return ResponseEntity containing the updated Wishlist and HTTP status code, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlist) {

        Wishlist updatedWishlist = wishlistService.update(wishlist);
        if (updatedWishlist != null) {
            return ResponseEntity.ok(updatedWishlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a wishlist item by its ID.
     *
     * @param id the ID of the wishlist item to delete
     * @return ResponseEntity with HTTP status code indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all wishlist items.
     *
     * @return ResponseEntity containing the list of all Wishlists and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistService.findAll();
        return ResponseEntity.ok(wishlists);
    }
}
