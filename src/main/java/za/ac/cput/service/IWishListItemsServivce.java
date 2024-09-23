package za.ac.cput.service;

import za.ac.cput.domain.WishListItem;

import java.util.List;

/**
 * IWishListItemsServivce.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Sep-24
 */

public interface IWishListItemsServivce extends IService<WishListItem, Long> {
    void deleteByWishlistId(Long wishlistId);
}
