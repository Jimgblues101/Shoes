package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Factory class for creating instances of {@link Category}.
 * Provides static methods to create {@link Category} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CategoryFactory {

    /**
     * Creates a {@link Category} instance from various inputs.
     *
     * @param id          the ID of the category
     * @param name        the name of the category
     * @param description the description of the category
     * @param createdAt   the date the category was created
     * @param deletedAt   the date the category was deleted (if applicable)
     * @return a new {@link Category} object with properties set from the input parameters
     */
    public static Category createCategory(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime deletedAt) {
        // Define constants for the switch cases
        final int NAME_EMPTY = 1;
        final int DESCRIPTION_EMPTY = 2;

        // Calculate the errorFlags based on null or empty checks
        int errorFlags = 0;

        if (Helper.isNullOrEmpty(name)) {
            errorFlags |= NAME_EMPTY;
        }
        if (Helper.isNullOrEmpty(description)) {
            errorFlags |= DESCRIPTION_EMPTY;
        }

        // Use switch statement to throw exception based on the flags
        switch (errorFlags) {
            case NAME_EMPTY | DESCRIPTION_EMPTY:
                throw new IllegalArgumentException("Name and description cannot be null or empty");
            case NAME_EMPTY:
                throw new IllegalArgumentException("Name cannot be null or empty");
            case DESCRIPTION_EMPTY:
                throw new IllegalArgumentException("Description cannot be null or empty");
            default:
                // No null or empty values
                break;
        }

        // Use the Builder pattern to create a new Category object
        return new Category.Builder()
                .setId(id) // Set the ID of the category
                .setName(name) // Set the name of the category
                .setDescription(description) // Set the description of the category
                .setCreatedAt(createdAt) // Set the date the category was created
                .setDeletedAt(LocalDateTime.now()) // Set the date the category was deleted (if applicable)
                .build();
    }
}
