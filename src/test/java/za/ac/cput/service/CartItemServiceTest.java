package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.enums.ProductAttributeType;
import za.ac.cput.factory.*;
import za.ac.cput.repository.CartItemRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private CartItemRepository cartItemRepository;

    private Cart cart;
    private Product product;
    private ProductSku productSku;
    private Set<String> roles;
    private User user;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        roles = new HashSet<>();
        roles.add("Admin");
        roles.add("User");

        user = UserFactory.createUser(
                null,
                "avatar.jpg",
                "John",
                "Doe",
                "johndoe@example.com",
                LocalDate.parse("1990-01-01"),
                roles,
                "0123456789",
                "password123");

        cart = CartFactory.createCart(
                1L,
                user,
                800.0,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        // Create a sample Product
        ImageUrls imageUrls = ImageUrlsFactory.createImageUrls(
                "image1.jpg",
                "image2.jpg",
                "image3.jpg",
                "image4.jpg"
        );

        Category category = CategoryFactory.createCategory(
                1L,
                "Sneakers",
                "Sneakers",
                LocalDateTime.now(),
                null
        );

        SubCategory subCategory = SubCategoryFactory.createSubCategory(
                1L,
                category,
                "High Tops",
                "High Top Sneakers",
                LocalDateTime.now(),
                null
        );

        product = ProductFactory.createProduct(
                1L,
                "Product Name",
                "Product Description",
                "Product Summary",
                "Product Cover",
                imageUrls,
                List.of(subCategory),
                LocalDateTime.now(),
                null
        );

        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null
        );

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null
        );

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null
        );

        productSku = ProductSkuFactory.createProductSku(
                null,
                product,
                sizeAttribute,
                colorAttribute,
                brandAttribute,
                "SKU-123",
                100.0,
                10,
                LocalDateTime.now(),
                null
        );

        // Create a CartItem
        cartItem = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                productSku,
                2 // quantity
        );
    }

    @AfterEach
    void tearDown() {
        cartItemRepository.deleteAll();
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
        CartItem createdItem = cartItemService.create(cartItem);

        createdItem = new CartItem.Builder()
                .copy(createdItem)
                .setCart(createdItem.getCart())
                .setQuantity(3)
                .build()
        ;
        CartItem updatedItem = cartItemService.update(createdItem);
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
