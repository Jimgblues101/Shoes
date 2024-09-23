package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.WishListItem;
import za.ac.cput.domain.Wishlist;
import za.ac.cput.repository.WishListItemRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WishListItemServiceTest {

    @Autowired
    private WishListItemService service;

    @Autowired
    private WishListItemRepository repository;

    private Product product;
    private Wishlist wishlist;
    private List<WishListItem> wishListItems;

    @Autowired
    private ProductService productService;

    @Autowired
    private WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        product = productService.read(1L);  // Fetching a product from the database
        wishlist = wishlistService.read(1L); // Fetching a wishlist from the database

        // Create WishListItems
        WishListItem item1 = new WishListItem.Builder()
                .setProduct(product)
                .setDateAdded(LocalDateTime.now())
                .setWishlist(wishlist)
                .build();

        WishListItem item2 = new WishListItem.Builder()
                .setProduct(product)
                .setDateAdded(LocalDateTime.now())
                .setWishlist(wishlist)
                .build();

        wishListItems = List.of(item1, item2);

        wishlist = new Wishlist.Builder()
                .copy(wishlist)
                .setWishlistItems(wishListItems)
                .build();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll(); // Optional, to clean up after each test
    }

    @Test
    @Order(1)
    void create() {
        // Test creating a new wishlist item
        WishListItem createdItem = service.create(wishListItems.get(0));
        assertNotNull(createdItem);
        assertEquals(wishListItems.get(0).getProduct().getId(), createdItem.getProduct().getId());
        assertEquals(wishListItems.get(0).getWishlist().getId(), createdItem.getWishlist().getId());
        assertEquals(wishListItems.get(0).getDateAdded(), createdItem.getDateAdded());
    }

    @Test
    @Order(2)
    void read() {
        // Test reading a wishlist item by ID
        WishListItem createdItem = service.create(wishListItems.get(0));
        WishListItem readItem = service.read(createdItem.getId());
        assertNotNull(readItem);
        assertEquals(createdItem.getId(), readItem.getId());
    }

    @Test
    @Order(3)
    void update() {
        // Test updating a wishlist item
        WishListItem createdItem = service.create(wishListItems.get(0));
        WishListItem updatedItem = new WishListItem.Builder()
                .copy(createdItem)
                .setDateAdded(LocalDateTime.now().plusDays(1)) // Update the date
                .build();

        WishListItem result = service.update(updatedItem);
        assertNotNull(result);
        assertEquals(updatedItem.getDateAdded(), result.getDateAdded());
    }

    @Test
    @Order(4)
    void findAll() {
        // Test finding all wishlist items
        service.create(wishListItems.get(0));
        service.create(wishListItems.get(1));
        List<WishListItem> items = service.findAll();
        assertFalse(items.isEmpty());
    }

    @Test
    @Order(5)
    void delete() {
        // Test deleting a wishlist item by ID
        WishListItem createdItem = service.create(wishListItems.get(0));
        service.delete(createdItem.getId());
        WishListItem deletedItem = service.read(createdItem.getId());
        assertNull(deletedItem); // The item should be deleted, so reading should return null
    }
}
