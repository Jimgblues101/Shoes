package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WishlistFactoryTest {

    private User user;
    private Product product;
    private Wishlist wishlist;
    private List<WishListItem> wishListItems;
    private Category category;
    private List<SubCategory> subCategory;
    private ImageUrls imageUrls;

    @BeforeEach
    void setup() {
        wishlist = new Wishlist();
        // Create a sample Category object using the factory method
        category = CategoryFactory.createCategory(
                1L,
                "Sneakers",
                "Sneakers",
                LocalDateTime.now(),
                null);

        // Create sample SubCategory objects using the factory method
        SubCategory subCategory1 = SubCategoryFactory.createSubCategory(
                1L,
                category,
                "High Tops",
                "High Top Sneakers",
                LocalDateTime.now(),
                null);

        SubCategory subCategory2 = SubCategoryFactory.createSubCategory(
                2L,
                category,
                "Low Tops",
                "Low Top Sneakers",
                LocalDateTime.now(),
                null);

        subCategory = List.of(subCategory1, subCategory2);

        // Create a sample ImageUrls object using the factory method
        imageUrls = ImageUrlsFactory.createImageUrls(
                "image1.jpg",
                "image2.jpg",
                "image3.jpg",
                "image4.jpg"
        );

        // Create a sample Product object using the factory method
        product = ProductFactory.createProduct(
                1L,
                "Product Name",
                "Product Description",
                "Product Summary",
                "Product Cover",
                imageUrls,
                subCategory,
                LocalDateTime.now(),
                null);

        // Set up a sample User object
        user = new User.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("johndoe@example.com")
                .setPassword("password123")
                .build();

        // Create WishListItem objects using the factory method
        WishListItem wishlistitem1 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        WishListItem wishlistitem2 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        wishListItems = new ArrayList<>();
        wishListItems.add(wishlistitem1);
        wishListItems.add(wishlistitem2);
    }

    @Test
    void testCreateWishlist() {
        // Create a Wishlist object using the factory method
        wishlist = WishlistFactory.createWishlist(
                1L,
                user,
                wishListItems,
                LocalDateTime.now(),
                null);

        // Verify that the Wishlist object is not null
        assertNotNull(wishlist);

        // Print the created Wishlist object to the terminal
        System.out.println("Created Wishlist: " + wishlist);
    }

    @Test
    void testCreateWishlist_WithNullUser_ThrowsIllegalArgumentException() {
        // Try to create a Wishlist object with null user
        assertThrows(IllegalArgumentException.class,
                () -> WishlistFactory.createWishlist(
                        1L,
                        null,
                        wishListItems,
                        LocalDateTime.now(),
                        null)
        );

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating Wishlist with null user");
    }

    @Test
    void testCreateWishlist_WithNullItems_ThrowsIllegalArgumentException() {
        // Try to create a Wishlist object with null wishlist items
        assertThrows(IllegalArgumentException.class,
                () -> WishlistFactory.createWishlist(
                        1L,
                        user,
                        null,
                        LocalDateTime.now(),
                        null)
        );

        System.out.println("Expected IllegalArgumentException thrown when creating Wishlist with null items");
    }
}
