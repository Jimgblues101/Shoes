package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressFactoryTest {

    @Test
    void testCreateAddress() {
        User user = new User();
        // Create a sample Address object using the factory method
        Address address = AddressFactory.createAddress(
                1L,
                user,
                "Home",
                "123 Main St",
                "Apt 101",
                "USA",
                "New York",
                "10001",
                "123-456-7890",
                LocalDateTime.now(),
                null);

        // Verify that the Address object is not null
        assertNotNull(address);
    }

    @Test
    void testCreateAddress_WithNullUser_ThrowsIllegalArgumentException() {
        // Try to create an Address object with a null User
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress(
                1L,
                null,
                "Home",
                "123 Main St",
                "Apt 101",
                "USA",
                "New York",
                "10001",
                "123-456-7890",
                LocalDateTime.now(),
                null));
    }
}