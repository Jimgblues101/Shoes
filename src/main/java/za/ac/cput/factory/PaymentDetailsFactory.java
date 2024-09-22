package za.ac.cput.factory;

import za.ac.cput.domain.OrderDetails;
import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Factory class for creating instances of {@link PaymentDetails}.
 * Provides static methods to create {@link PaymentDetails} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */
public class PaymentDetailsFactory {

    /**
     * Creates a {@link PaymentDetails} instance from various inputs.
     *
     * @param id          the ID of the payment details
     * @param orderDetails the {@link OrderDetails} entity associated with the payment details
     * @param amount      the amount of the payment
     * @param provider    the payment provider
     * @param status      the status of the payment
     * @param createdAt   the date the payment was created
     * @param updatedAt   the date the payment was updated (if applicable)
     * @return a new {@link PaymentDetails} object with properties set from the input parameters
     */
    public static PaymentDetails createPaymentDetails(Long id,
                                                      OrderDetails orderDetails,
                                                      Double amount,
                                                      String provider,
                                                      String status,
                                                      LocalDateTime createdAt,
                                                      LocalDateTime updatedAt) {
        // Define constants for the switch cases
        final int ORDER_DETAILS_NULL = 1;
        final int AMOUNT_NULL = 2;
        final int PROVIDER_NULL = 4;
        final int STATUS_NULL = 8;

        // Calculate the errorFlags based on null or empty checks
        int errorFlags = 0;

        if (Helper.isNullOrEmpty(orderDetails)) {
            errorFlags |= ORDER_DETAILS_NULL;
        }
        if (Helper.isDoubleNullOrEmpty(amount)) {
            errorFlags |= AMOUNT_NULL;
        }
        if (Helper.isNullOrEmpty(provider)) {
            errorFlags |= PROVIDER_NULL;
        }
        if (Helper.isNullOrEmpty(status)) {
            errorFlags |= STATUS_NULL;
        }

        // Use switch statement to throw exception based on the flags
        switch (errorFlags) {
            case ORDER_DETAILS_NULL | AMOUNT_NULL | PROVIDER_NULL | STATUS_NULL:
                throw new IllegalArgumentException("OrderDetails, amount, provider, and status cannot be null or empty");
            case ORDER_DETAILS_NULL | AMOUNT_NULL | PROVIDER_NULL:
                throw new IllegalArgumentException("OrderDetails, amount, and provider cannot be null or empty");
            case ORDER_DETAILS_NULL | AMOUNT_NULL | STATUS_NULL:
                throw new IllegalArgumentException("OrderDetails, amount, and status cannot be null or empty");
            case ORDER_DETAILS_NULL | PROVIDER_NULL | STATUS_NULL:
                throw new IllegalArgumentException("OrderDetails, provider, and status cannot be null or empty");
            case AMOUNT_NULL | PROVIDER_NULL | STATUS_NULL:
                throw new IllegalArgumentException("Amount, provider, and status cannot be null or empty");
            case ORDER_DETAILS_NULL | AMOUNT_NULL:
                throw new IllegalArgumentException("OrderDetails and amount cannot be null or empty");
            case ORDER_DETAILS_NULL | PROVIDER_NULL:
                throw new IllegalArgumentException("OrderDetails and provider cannot be null or empty");
            case ORDER_DETAILS_NULL | STATUS_NULL:
                throw new IllegalArgumentException("OrderDetails and status cannot be null or empty");
            case AMOUNT_NULL | PROVIDER_NULL:
                throw new IllegalArgumentException("Amount and provider cannot be null or empty");
            case AMOUNT_NULL | STATUS_NULL:
                throw new IllegalArgumentException("Amount and status cannot be null or empty");
            case PROVIDER_NULL | STATUS_NULL:
                throw new IllegalArgumentException("Provider and status cannot be null or empty");
            case ORDER_DETAILS_NULL:
                throw new IllegalArgumentException("OrderDetails cannot be null or empty");
            case AMOUNT_NULL:
                throw new IllegalArgumentException("Amount cannot be null or empty");
            case PROVIDER_NULL:
                throw new IllegalArgumentException("Provider cannot be null or empty");
            case STATUS_NULL:
                throw new IllegalArgumentException("Status cannot be null or empty");
            default:
                // No null or empty values
                break;
        }

        // Use the Builder pattern to create a new PaymentDetails object
        return new PaymentDetails.Builder()
                .setId(id) // Set the ID of the payment details
                .setOrderDetails(orderDetails) // Set the order details associated with the payment details
                .setAmount(amount) // Set the amount of the payment
                .setProvider(provider) // Set the payment provider
                .setStatus(status) // Set the status of the payment
                .setCreatedAt(createdAt) // Set the date the payment was created
                .setUpdatedAt(LocalDateTime.now()) // Set the date the payment was updated (if applicable)
                .build();
    }
}
