package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.OrderDetails;
import za.ac.cput.factory.OrderDetailsFactory;
import za.ac.cput.repository.OrderDetailsRepository;
import za.ac.cput.repository.OrderItemRepository;
import za.ac.cput.repository.PaymentDetailsRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderDetailsService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@Service
@Transactional
public class OrderDetailsService implements IOrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderItemRepository orderItemRepository, PaymentDetailsRepository paymentDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    @Override
    public OrderDetails create(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails read(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDetails update(OrderDetails updatedOrderDetails) {
        if (orderDetailsRepository.existsById(updatedOrderDetails.getId())) {
            OrderDetails existingOrderDetails = orderDetailsRepository.findById(updatedOrderDetails.getId()).orElse(null);
            if (existingOrderDetails != null) {
                OrderDetails orderDetailsToUpdate = OrderDetailsFactory.createOrderDetails(
                        updatedOrderDetails.getId(),
                        updatedOrderDetails.getUser(),
                        updatedOrderDetails.getPaymentDetails(),
                        updatedOrderDetails.getTotal(),
                        existingOrderDetails.getCreatedAt(), // Retain the original creation date
                        LocalDateTime.now() // Update the updatedAt field to current date
                );
                return orderDetailsRepository.save(orderDetailsToUpdate);
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteOrderItemByOrderDetails_Id(id);
        paymentDetailsRepository.deleteByOrderDetailsId(id);
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public List<OrderDetails> findByUserId(Long userId) {
        return orderDetailsRepository.findByUserId(userId);
    }

    @Override
    public List<OrderDetails> findByTotalGreaterThanEqual(Double total) {
        return orderDetailsRepository.findByTotalGreaterThanEqual(total);
    }

    @Override
    public List<OrderDetails> findByCreatedAtAfter(LocalDateTime createdAt) {
        return orderDetailsRepository.findByCreatedAtAfter(createdAt);
    }

    @Override
    public List<OrderDetails> findByUpdatedAtBefore(LocalDateTime updatedAt) {
        return orderDetailsRepository.findByUpdatedAtBefore(updatedAt);
    }

    @Override
    public List<OrderDetails> findByPaymentId(Long paymentId) {
        return orderDetailsRepository.findByPaymentDetails_Id(paymentId);
    }

    @Override
    public List<OrderDetails> findByUserIdOrderByCreatedAtDesc(Long userId) {
        return orderDetailsRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
