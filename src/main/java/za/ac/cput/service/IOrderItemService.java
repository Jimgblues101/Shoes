package za.ac.cput.service;


import za.ac.cput.domain.OrderItem;

/**
 * IOrderItemService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

public interface IOrderItemService extends IService<OrderItem, Long>{
    void deleteOrderItemByOrderDetails_Id(Long orderId);
}
