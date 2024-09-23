package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.repository.CartItemRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest
@ActiveProfiles("test") // Use a specific profile for testing if needed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = AFTER_CLASS)
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    private Cart cart;
    private Product product;
    private ProductSku productSku;

    private User user;
    private CartItem cartItem;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {

        user = userService.read(61L);
        product = productService.read(59L);
        productSku = productSkuService.read(1L);

        cart = CartFactory.createCart(
                1L,
                user,
                800.0,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        cart = cartService.create(cart);
        Cart carts = cartService.read(cart.getId());
        // Create a CartItem
        cartItem = CartItemFactory.createCartItem(
                null,
                carts,
                product,
                productSku,
                2
        );
    }

    @AfterEach
    void tearDown() {
        //   cartItemRepository.deleteAll();
    }

    @Test
    @Order(1)
    void create() {
        CartItem createdItem = cartItemService.create(cartItem);
        assertNotNull(createdItem);
        assertEquals(cartItem.getQuantity(), createdItem.getQuantity());
    }

    @Test
    @Order(2)
    void read() {
        CartItem createdItem = cartItemService.create(cartItem);
        CartItem readItem = cartItemService.read(createdItem.getId());
        assertNotNull(readItem);
        assertEquals(createdItem.getId(), readItem.getId());
    }

    @Test
    @Order(3)
    void update() {
        // Create a CartItem first to ensure we have an item to update
        CartItem createdItem = cartItemService.create(cartItem);

        // Read the item we just created
        CartItem item = cartItemService.read(createdItem.getId());
        assertNotNull(item); // Ensure the item exists

        // Log the current state for debugging
        System.out.println("Current Item: " + item);

        // Create an updated item based on the original
        CartItem updatedCartItem = new CartItem.Builder()
                .copy(item)
                .setQuantity(3) // Set new quantity
                .build();

        // Update the item
        CartItem updatedItem = cartItemService.update(updatedCartItem);

        // Check that the updated quantity is as expected
        assertEquals(3, updatedItem.getQuantity());
    }

    @Test
    @Order(4)
    void delete() {
        CartItem createdItem = cartItemService.create(cartItem);
        cartItemService.delete(createdItem.getId());
        assertNull(cartItemService.read(createdItem.getId()));
    }

    @Test
    @Order(5)
    void findAll() {
        cartItemService.create(cartItem);
        List<CartItem> cartItems = cartItemService.findAll();
        assertFalse(cartItems.isEmpty());
    }

    @Test
    @Order(6)
    void findByCartId() {
        cartItemService.create(cartItem);
        List<CartItem> items = cartItemService.findByCartId(cart.getId());
        assertFalse(items.isEmpty());
        assertEquals(cart.getId(), items.get(0).getCart().getId());
    }

    @Test
    @Order(7)
    void findByProductId() {
        cartItemService.create(cartItem);
        List<CartItem> items = cartItemService.findByProductId(product.getId());
        assertFalse(items.isEmpty());
        assertEquals(product.getId(), items.get(0).getProduct().getId());
    }

    @Test
    @Order(8)
    void findByProductSkuId() {
        cartItemService.create(cartItem);
        List<CartItem> items = cartItemService.findByProductSkuId(productSku.getId());
        assertFalse(items.isEmpty());
        assertEquals(productSku.getId(), items.get(0).getProductSku().getId());
    }

    @Test
    @Order(9)
    void findByQuantity() {
        cartItemService.create(cartItem);
        List<CartItem> items = cartItemService.findByQuantity(cartItem.getQuantity());
        assertFalse(items.isEmpty());
        assertEquals(cartItem.getQuantity(), items.get(0).getQuantity());
    }
}
