package za.ac.cput.factory;

import za.ac.cput.domain.OrderDetails;
import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Factory class for creating instances of {@link OrderDetails}.
 * Provides static methods to create {@link OrderDetails} objects from various inputs.
 *
 * Author: Rethabile Ntsekhe
 * Date: 25-Aug-24
 */
public class OrderDetailsFactory {

    /**
     * Creates a {@link OrderDetails} instance from various inputs.
     *
     * @param id              the ID of the order details
     * @param user          the ID of the {@link User} entity associated with the order
     * @param paymentDetails  the ID of the {@link PaymentDetails} entity associated with the order
     * @param total           the total cost of the order
     * @param createdAt       the date the order was created
     * @param updatedAt       the date the order was updated (if applicable)
     * @return a new {@link OrderDetails} object with properties set from the input parameters
     */
    public static OrderDetails createOrderDetails(
            Long id,
            User user,
            PaymentDetails paymentDetails,
            Double total,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        // Define constants for the switch cases
        final int USER_NULL = 1;
        final int PAYMENT_DETAILS_NULL = 2;
        final int TOTAL_NULL = 4;

        // Calculate the errorFlags based on null or empty checks
        int errorFlags = 0;

        if (Helper.isNullOrEmpty(user)) {
            errorFlags |= USER_NULL;
        }
        if (Helper.isNullOrEmpty(paymentDetails)) {
            errorFlags |= PAYMENT_DETAILS_NULL;
        }
        if (Helper.isDoubleNullOrEmpty(total)) {
            errorFlags |= TOTAL_NULL;
        }

        // Use switch statement to throw exception based on the flags
        switch (errorFlags) {
            case USER_NULL | PAYMENT_DETAILS_NULL | TOTAL_NULL:
                throw new IllegalArgumentException("User, payment details, and total cannot be null");
            case USER_NULL | PAYMENT_DETAILS_NULL:
                throw new IllegalArgumentException("User and payment details cannot be null");
            case USER_NULL | TOTAL_NULL:
                throw new IllegalArgumentException("User and total cannot be null");
            case PAYMENT_DETAILS_NULL | TOTAL_NULL:
                throw new IllegalArgumentException("Payment details and total cannot be null");
            case USER_NULL:
                throw new IllegalArgumentException("User cannot be null");
            case PAYMENT_DETAILS_NULL:
                throw new IllegalArgumentException("Payment details cannot be null");
            case TOTAL_NULL:
                throw new IllegalArgumentException("Total cannot be null");
            default:
                // No null or empty values
                break;
        }

        // Use the Builder pattern to create a new OrderDetails object
        return new OrderDetails.Builder()
                .setId(id) // Set the ID of the order details
                .setUser(user) // Set the user associated with the order
                .setPaymentDetails(paymentDetails) // Set the payment details associated with the order
                .setTotal(total) // Set the total cost of the order
                .setCreatedAt(createdAt) // Set the date the order was created
                .setUpdatedAt(LocalDateTime.now()) // Set the date the order was updated (if applicable)
                .build();
    }
}
