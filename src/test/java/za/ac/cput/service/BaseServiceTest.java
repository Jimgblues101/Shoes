package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.enums.ProductAttributeType;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public abstract class BaseServiceTest {

    @Autowired
    protected CartItemService cartItemService;

    protected Cart cart;
    protected Product product;
    protected ProductSku productSku;
    protected Set<String> roles;
    protected User user;
    protected CartItem cartItem1;
    protected CartItem cartItem2; // Changed to instance variables
    protected Address address;
    protected OrderDetails orderDetails;
    protected OrderItem orderItem;
    protected PaymentDetails paymentDetails;
    protected Wishlist wishlist;
    protected List<WishListItem> wishListItems;

    @BeforeEach
    void setUp() {
        // Initialize user roles
        roles = new HashSet<>();
        roles.add("Admin");
        roles.add("User");

        // Create a sample User object
        user = UserFactory.createUser(
                null,
                "avatar.jpg",
                "Rethabile",
                "Ntsekhe",
                "servicetest@address.com",
                LocalDate.parse("1990-01-01"),
                roles,
                "0123456789",
                "password123"
        );

        // Set up an Address object associated with the user
        address = new Address.Builder()
                .setId(1L)
                .setUser(user)
                .setTitle("Home")
                .setAddressLine1("Apt 101")
                .setAddressLine2("New York")
                .setCountry("South Africa")
                .setCity("Cape Town")
                .setPostalCode("9320")
                .setPhoneNumber( "1234567890")
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(null)
                .build();

        // Set up the Cart
        cart = CartFactory.createCart(
                1L,
                user,
                800.0,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        // Set up the Product
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
                "AirForce 1",
                "All White AirForce 1",
                "Nike AirForce 1",
                "cover img url",
                imageUrls,
                List.of(subCategory),
                LocalDateTime.now(),
                null
        );

        // Set up Product attributes
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

        // Create Product SKU
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

        // Set up CartItems
        cartItem1 = CartItemFactory.createCartItem(
                1L,
                cart,
                product,
                productSku,
                2
        );

        cartItem2 = CartItemFactory.createCartItem(
                2L,
                cart,
                product,
                productSku,
                10
         );

        // Set up OrderDetails
        orderDetails = OrderDetailsFactory.createOrderDetails(
                1L,
                user,
                paymentDetails,
                100.0,
                LocalDateTime.parse("2024-06-12T00:00:00"),
                LocalDateTime.parse("2024-06-12T00:00:00")
        );

        // Set up PaymentDetails
        paymentDetails = PaymentDetailsFactory.createPaymentDetails(
                1L,
                orderDetails,
                100.0,
                "PayPal",
                "Success",
                LocalDateTime.parse("2024-06-12T12:00:00"),
                LocalDateTime.parse("2024-06-12T12:00:00")
        );

        // Set up OrderItem
        orderItem = OrderItemFactory.createOrderItem(
                1L,
                orderDetails,
                product,
                productSku,
                2,
                LocalDateTime.parse("2024-06-12T08:00"),
                LocalDateTime.parse("2024-06-12T08:00")
        );

        // Set up Wishlist and WishlistItems
        WishListItem wishlistItem1 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        WishListItem wishlistItem2 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        wishListItems = new ArrayList<>();
        wishListItems.add(wishlistItem1);
        wishListItems.add(wishlistItem2);

        wishlist = WishlistFactory.createWishlist(
                1L,
                user,
                wishListItems,
                LocalDateTime.now(),
                null
        );
    }
}
