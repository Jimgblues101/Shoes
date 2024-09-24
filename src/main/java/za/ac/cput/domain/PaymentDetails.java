package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents payment details within the system.
 * This class is mapped to the "payment_details" table in the database.
 *
 * Stores only the ID of the associated OrderDetails entity.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */
@Entity
@Getter
@Table(name = "payment_details")
public class PaymentDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    private Double amount;
    private String provider;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PaymentDetails() {}

    private PaymentDetails(Builder builder) {
        this.id = builder.id;
        this.orderDetails = builder.orderDetails;
        this.amount = builder.amount;
        this.provider = builder.provider;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    @Override
    public String toString() {
        return "\n PaymentDetails{" +
                "id=" + id +
                ", orderDetails=" + orderDetails +
                ", amount=" + amount +
                ", provider='" + provider + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDetails that = (PaymentDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderDetails, that.orderDetails) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(provider, that.provider) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDetails, amount, provider, status, createdAt, updatedAt);
    }

    public static class Builder {
        private Long id;
        private OrderDetails orderDetails;
        private Double amount;
        private String provider;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrderDetails(OrderDetails orderDetails) {
            this.orderDetails = orderDetails;
            return this;
        }

        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
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

        public Builder copy(PaymentDetails paymentDetails) {
            this.id = paymentDetails.getId();
            this.orderDetails = paymentDetails.getOrderDetails();
            this.amount = paymentDetails.getAmount();
            this.provider = paymentDetails.getProvider();
            this.status = paymentDetails.getStatus();
            this.createdAt = paymentDetails.getCreatedAt();
            this.updatedAt = paymentDetails.getUpdatedAt();
            return this;
        }

        public PaymentDetails build() {
            return new PaymentDetails(this);
        }
    }
}
