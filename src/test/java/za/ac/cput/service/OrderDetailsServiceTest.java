package za.ac.cput.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import za.ac.cput.Application;
import za.ac.cput.domain.OrderDetails;
import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderDetailsFactory;
import za.ac.cput.factory.PaymentDetailsFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OrderDetailsServiceTest {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    private OrderDetails orderDetails;
    private User testUser;
    private PaymentDetails testPaymentDetails;

    @BeforeEach
    void setUp() {
        // Set up a user for order details (assuming a valid test user exists)
        testUser = userService.read(1L); // Mock the user ID as needed or create a new user

        // Create a payment detail using the factory
        testPaymentDetails = PaymentDetailsFactory.createPaymentDetails(
                null,
                null,
                100.0,
                "Visa",
                "Success",
                LocalDateTime.now(),
                null
        );

        // Persist the payment details
        paymentDetailsService.create(testPaymentDetails);

        // Set up order details with valid foreign keys
        orderDetails = OrderDetailsFactory.createOrderDetails(
                null,
                testUser,
                testPaymentDetails,
                100.0,
                LocalDateTime.now(),
                null
        );

        // Persist the order details
        orderDetailsService.create(orderDetails);
    }

    @AfterEach
    void tearDown() {
        // Clean up test data by deleting the created order
        if (orderDetails.getId() != null) {
            orderDetailsService.delete(orderDetails.getId());
        }
    }

    @Test
    void create() {
        OrderDetails newOrder = OrderDetailsFactory.createOrderDetails(
                null,
                testUser,
                testPaymentDetails,
                200.0,
                LocalDateTime.now(),
                null
        );
        OrderDetails createdOrder = orderDetailsService.create(newOrder);

        // Verify the order was created
        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getId());
        assertEquals(1L, createdOrder.getUser().getId());
        assertEquals(200.0, createdOrder.getTotal());
    }

    @Test
    void read() {
        OrderDetails foundOrder = orderDetailsService.read(orderDetails.getId());
        assertNotNull(foundOrder);
        assertEquals(orderDetails.getId(), foundOrder.getId());
    }

    @Test
    void update() {
        // Create the order before attempting an update
        OrderDetails createdOrder = orderDetailsService.create(orderDetails);

        // Create an updated order details object
        OrderDetails updatedOrder = new OrderDetails.Builder()
                .copy(orderDetails)
                .setTotal(150.0)
                .build();

        // Perform the update
        OrderDetails result = orderDetailsService.update(updatedOrder);

        // Verify the updated fields
        assertNotNull(result);
        assertEquals(150.0, result.getTotal());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void delete() {
        orderDetailsService.delete(orderDetails.getId());
        OrderDetails deletedOrder = orderDetailsService.read(orderDetails.getId());
        assertNull(deletedOrder); // Verify that the order has been deleted
    }

    @Test
    void findAll() {
        List<OrderDetails> orders = orderDetailsService.findAll();
        assertFalse(orders.isEmpty());
    }

    @Test
    void findByUserId() {
        List<OrderDetails> orders = orderDetailsService.findByUserId(orderDetails.getUser().getId());
        assertFalse(orders.isEmpty());
        assertEquals(1L, orders.get(0).getUser().getId());
    }

    @Test
    void findByTotalGreaterThanEqual() {
        List<OrderDetails> orders = orderDetailsService.findByTotalGreaterThanEqual(100.0);
        assertFalse(orders.isEmpty());
        assertTrue(orders.stream().allMatch(order -> order.getTotal() >= 100.0));
    }

    @Test
    void findByCreatedAtAfter() {
        List<OrderDetails> orders = orderDetailsService.findByCreatedAtAfter(LocalDateTime.now().minusDays(1));
        assertFalse(orders.isEmpty());
    }

    @Test
    void findByUpdatedAtBefore() {
        List<OrderDetails> orders = orderDetailsService.findByUpdatedAtBefore(LocalDateTime.now());
        assertTrue(orders.isEmpty());
    }

    @Test
    void findByPaymentId() {
        List<OrderDetails> orders = orderDetailsService.findByPaymentId(testPaymentDetails.getId());
        assertFalse(orders.isEmpty());
        assertEquals(testPaymentDetails.getId(), orders.get(0).getPaymentDetails().getId());
    }

    @Test
    void findByUserIdOrderByCreatedAtDesc() {
        List<OrderDetails> orders = orderDetailsService.findByUserIdOrderByCreatedAtDesc(orderDetails.getUser().getId());
        System.out.println(orders);
        assertFalse(orders.isEmpty());
    }
}
