package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;
import za.ac.cput.enums.ProductAttributeType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an attribute of a product.
 * This entity class is mapped to the "product_attributes" table in the database.
 * It is immutable and uses the builder pattern for construction.
 *
 * Author: Rethabile Ntsekhe
 * Date: 25-Aug-24
 */
@Entity
@Getter
@Table(name = "product_attributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductAttributeType type;

    @Column(nullable = false)
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ProductAttribute() {
    }

    private ProductAttribute(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.value = builder.value;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    @Override
    public String toString() {
        return "\n ProductAttribute{" +
                "id=" + id +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}\n ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAttribute that = (ProductAttribute) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, createdAt, updatedAt);
    }

    public static class Builder {
        private Long id;
        private ProductAttributeType type;
        private String value;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setType(ProductAttributeType type) {
            this.type = type;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder copy(ProductAttribute productAttribute) {
            this.id = productAttribute.id;
            this.type = productAttribute.type;
            this.value = productAttribute.value;
            this.createdAt = productAttribute.createdAt;
            this.updatedAt = productAttribute.updatedAt;
            return this;
        }

        public ProductAttribute build() {
            return new ProductAttribute(this);
        }
    }
}