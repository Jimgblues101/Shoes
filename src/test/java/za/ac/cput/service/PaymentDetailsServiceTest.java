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
import za.ac.cput.factory.OrderDetailsFactory;
import za.ac.cput.factory.PaymentDetailsFactory;
import za.ac.cput.repository.PaymentDetailsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = AFTER_CLASS)
class PaymentDetailsServiceTest {

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;
    private PaymentDetails paymentDetails;

    private OrderDetails orderDetails;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @BeforeEach
    void setUp() {

        paymentDetails = PaymentDetailsFactory.createPaymentDetails(
                null, // ID will be auto-generated
                orderDetails,
                1000.00,
                "PayPal",
                "Paid",
                LocalDateTime.now(),
                null
        );
        paymentDetailsService.create(paymentDetails);
    }

    @AfterEach
    void tearDown() {
        paymentDetailsRepository.deleteAll();
    }

    @Test
    void create() {
        PaymentDetails newPaymentDetails = PaymentDetailsFactory.createPaymentDetails(
                null,
                orderDetails,
                1500.00,
                "Credit Card",
                "Pending",
                LocalDateTime.now(),
                null
        );
        PaymentDetails created = paymentDetailsService.create(newPaymentDetails);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Credit Card", created.getProvider());
    }

    @Test
    void read() {
        PaymentDetails readPaymentDetails = paymentDetailsService.read(paymentDetails.getId());
        assertNotNull(readPaymentDetails);
        assertEquals(paymentDetails.getId(), readPaymentDetails.getId());
    }

    @Test
    void update() {
        paymentDetails = new PaymentDetails.Builder()
                .copy(paymentDetails)
                .setId(paymentDetails.getId())
                .setAmount(2000.00)
                .build();
        PaymentDetails updatedPaymentDetails = paymentDetailsService.update(paymentDetails);
        System.out.println("----------------------------------------------------------------");
        System.out.println(updatedPaymentDetails);
        assertNotNull(updatedPaymentDetails);
        assertEquals(2000.00, updatedPaymentDetails.getAmount());
    }

    @Test
    void delete() {
        paymentDetailsService.delete(paymentDetails.getId());
        PaymentDetails deletedPaymentDetails = paymentDetailsService.read(paymentDetails.getId());
        assertNull(deletedPaymentDetails);
    }

    @Test
    void findAll() {
        List<PaymentDetails> paymentDetailsList = paymentDetailsService.findAll();
        assertFalse(paymentDetailsList.isEmpty());
    }

    @Test
    void findByProvider() {
        List<PaymentDetails> paymentsByProvider = paymentDetailsService.findByProvider("PayPal");
        System.out.println("------------------------------------------------------------------");
        System.out.println(paymentsByProvider);
        assertFalse(paymentsByProvider.isEmpty());
        assertEquals("PayPal", paymentsByProvider.get(0).getProvider());
    }

    @Test
    void findByStatus() {
        List<PaymentDetails> paymentsByStatus = paymentDetailsService.findByStatus("Paid");
        assertFalse(paymentsByStatus.isEmpty());
        assertEquals("Paid", paymentsByStatus.get(0).getStatus());
    }

    @Test
    void findByCreatedAtAfter() {
        List<PaymentDetails> paymentsAfterDate = paymentDetailsService.findByCreatedAtAfter(LocalDateTime.now().minusDays(1));
        assertFalse(paymentsAfterDate.isEmpty());
    }

    @Test
    void findByCreatedAtBetween() {
        List<PaymentDetails> paymentsBetweenDates = paymentDetailsService.findByCreatedAtBetween(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );
        assertFalse(paymentsBetweenDates.isEmpty());
    }

    @Test
    void countByStatus() {
        long count = paymentDetailsService.countByStatus("Paid");
        assertTrue(count > 0);
    }

    @Test
    void deleteByOrderDetailsId() {
        int deletedCount = paymentDetailsService.deleteByOrderDetailsId(1L);
        assertTrue(deletedCount > 0);
    }
}
