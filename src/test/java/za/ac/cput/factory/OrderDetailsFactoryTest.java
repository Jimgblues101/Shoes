package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderDetails;
import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDetailsFactoryTest {

    private OrderDetails orderDetails;
    private User user;
    private PaymentDetails paymentDetails;

    @BeforeEach
    void setup() {
        // Set up sample User and PaymentDetails objects
        user = new User(); // Initialize a sample User object
        paymentDetails = new PaymentDetails(); // Initialize a sample PaymentDetails object

        // Set up a sample OrderDetails object using the factory method
        orderDetails = OrderDetailsFactory.createOrderDetails(
                1L,
                user,
                paymentDetails,
                100.0,
                LocalDateTime.parse("2024-06-12T00:00:00"),
                LocalDateTime.parse("2024-06-12T00:00:00"));
    }

    @Test
    void testCreateOrderDetails() {
        // Verify that the OrderDetails object is not null
        assertNotNull(orderDetails);

        // Print the created OrderDetails object to the terminal
        System.out.println("Created OrderDetails: " + orderDetails);
    }

    @Test
    void testCreateOrderDetails_WithNullUser_ThrowsIllegalArgumentException() {
        // Try to create an OrderDetails object with a null User
        assertThrows(IllegalArgumentException.class,
                () -> OrderDetailsFactory.createOrderDetails(
                        1L,
                        null,
                        paymentDetails,
                        100.0,
                        LocalDateTime.parse("2024-06-12T00:00:00"),
                        LocalDateTime.parse("2024-06-12T00:00:00")));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating OrderDetails with null User");
    }

    @Test
    void testCreateOrderDetails_WithNullPaymentDetails_ThrowsIllegalArgumentException() {
        // Try to create an OrderDetails object with null PaymentDetails
        assertThrows(IllegalArgumentException.class,
                () -> OrderDetailsFactory.createOrderDetails(
                        1L,
                        user,
                        null,
                        100.0,
                        LocalDateTime.parse("2024-06-12T00:00:00"),
                        LocalDateTime.parse("2024-06-12T00:00:00")));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating OrderDetails with null PaymentDetails");
    }

    @Test
    void testCreateOrderDetails_WithNullTotal_ThrowsIllegalArgumentException() {
        // Try to create an OrderDetails object with a null total
        assertThrows(IllegalArgumentException.class,
                () -> OrderDetailsFactory.createOrderDetails(
                        1L,
                        user,
                        paymentDetails,
                        null,
                        LocalDateTime.parse("2024-06-12T07:00:00"),
                        LocalDateTime.parse("2024-06-12T07:00:00")));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating OrderDetails with null total");
    }
}