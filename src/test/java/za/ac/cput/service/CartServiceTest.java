package za.ac.cput.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

class CartServiceTest {
    private Cart cart;
    private User user;

    @BeforeEach
    void setUp() {
        // Create a sample User object
        user = new User.Builder()
                .setFirstName("Rethabile")
                .setLastName("Ntsekhe")
                .setEmail("rethabile1154@gmail.com") // Ensure email matches used in tests
                .setPassword("password") // Use encoded password
                .setRole(Set.of("USER", "ADMIN"))
                .setBirthDate(LocalDate.of(1990, 1, 1))
                .setPhoneNumber("1234567890")
                .build();

        // Create a sample Cart object using the factory method
        cart = CartFactory.createCart(
                1L,
                user,
                100.0,
                LocalDateTime.now(),
                null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}