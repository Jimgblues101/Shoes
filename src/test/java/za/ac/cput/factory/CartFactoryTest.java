package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CartFactoryTest {

    @Test
    void testCreateCart() {
        // Create a sample User object
        User user = new User();

        // Create a sample Cart object using the factory method
        Cart cart = CartFactory.createCart(
                1L,
                user,
                100.0,
                LocalDateTime.now(),
                null);

        // Verify that the Cart object is not null
        assertNotNull(cart);

        // Print the created Cart object to the terminal
        System.out.println("Created Cart: " + cart);
    }

    @Test
    void testCreateCart_WithNullUser_ThrowsIllegalArgumentException() {
        // Try to create a Cart object with a null User
        assertThrows(IllegalArgumentException.class,
                () -> CartFactory.createCart(
                        1L,
                        null,
                        100.0,
                        LocalDateTime.now(),
                        null));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating Cart with null User");
    }

    @Test
    void testCreateCart_WithNullTotal_ThrowsIllegalArgumentException() {
        // Create a sample User object
        User user = new User();

        // Try to create a Cart object with a null total
        assertThrows(IllegalArgumentException.class, () -> CartFactory.createCart(
                1L,
                user,
                null,
                LocalDateTime.now(),
                null));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating Cart with null total");
    }
}