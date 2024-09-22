package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * WishListItem.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 20-Sep-24
 */

@Entity
@Getter
@Table(name = "wish_list_items")
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private LocalDateTime dateAdded;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    public WishListItem() {
    }

    public WishListItem(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.dateAdded = builder.dateAdded;
        this.wishlist = builder.wishlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishListItem that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(dateAdded, that.dateAdded) && Objects.equals(wishlist, that.wishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, dateAdded, wishlist);
    }

    @Override
    public String toString() {
        return "\n WishListItem{" +
                "id=" + id +
                ", product=" + product.getName() +
                ", dateAdded=" + dateAdded +
                ", wishlist=" + wishlist +
                "}\n";
    }

    public static class Builder {
        private Long id;
        private Product product;
        private LocalDateTime dateAdded;
        private Wishlist wishlist;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setDateAdded(LocalDateTime dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder setWishlist(Wishlist wishlist) {
            this.wishlist = wishlist;
            return this;
        }

        public Builder copy(WishListItem wishlistItem) {
            this.id = wishlistItem.id;
            this.product = wishlistItem.product;
            this.dateAdded = wishlistItem.dateAdded;
            this.wishlist = wishlistItem.wishlist;
            return this;
        }

        public WishListItem build() {
            return new WishListItem(this);
        }
    }
}
