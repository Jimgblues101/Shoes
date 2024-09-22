package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.factory.PaymentDetailsFactory;
import za.ac.cput.repository.PaymentDetailsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * PaymentDetailsService.java
 *
 * Service implementation for managing {@link PaymentDetails} entities.
 * Provides CRUD operations and additional methods for business logic.
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */
@Service
@Transactional
public class PaymentDetailsService implements IPaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    @Override
    public PaymentDetails create(PaymentDetails paymentDetails) {
        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails read(Long id) {
        return paymentDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentDetails update(PaymentDetails paymentDetails) {
        if (paymentDetails == null || !paymentDetailsRepository.existsById(paymentDetails.getId())) {
            throw new IllegalArgumentException("PaymentDetails with the given ID does not exist.");
        }

        PaymentDetails existingPayment = paymentDetailsRepository.findById(paymentDetails.getId()).orElseThrow();

        PaymentDetails updatedPaymentDetails = PaymentDetailsFactory.createPaymentDetails(
                existingPayment.getId(),
                paymentDetails.getOrderDetails(),
                paymentDetails.getAmount(),
                paymentDetails.getProvider(),
                paymentDetails.getStatus(),
                existingPayment.getCreatedAt(),
                LocalDateTime.now()
        );

        return paymentDetailsRepository.save(updatedPaymentDetails);
    }

    @Override
    public void delete(Long id) {
        paymentDetailsRepository.deleteById(id);
    }

    @Override
    public List<PaymentDetails> findAll() {
        return paymentDetailsRepository.findAll();
    }

    @Override
    public List<PaymentDetails> findByProvider(String provider) {
        return paymentDetailsRepository.findByProvider(provider);
    }

    @Override
    public List<PaymentDetails> findByStatus(String status) {
        return paymentDetailsRepository.findByStatus(status);
    }

    @Override
    public List<PaymentDetails> findByCreatedAtAfter(LocalDateTime createdAt) {
        return paymentDetailsRepository.findByCreatedAtAfter(createdAt);
    }

    @Override
    public List<PaymentDetails> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentDetailsRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public long countByStatus(String status) {
        return paymentDetailsRepository.countByStatus(status);
    }

    @Override
    public int deleteByOrderDetailsId(Long orderDetailsId) {
        int deletedCount = paymentDetailsRepository.deleteByOrderDetailsId(orderDetailsId);
        if (deletedCount <= 0) {
            throw new IllegalArgumentException("No payment details found for the given order details ID");
        }
        return deletedCount;
    }
}
