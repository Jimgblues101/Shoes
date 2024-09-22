package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.repository.OrderItemRepository;

import java.util.List;

/**
 * OrderItemService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@Service
@Transactional
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem read(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        if (orderItemRepository.existsById(orderItem.getId())) {
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }
}