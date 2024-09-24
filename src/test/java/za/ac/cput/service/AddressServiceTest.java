package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import za.ac.cput.Application;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.AddressRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = AFTER_CLASS)
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    private Address address;
    private User user;
    private Set<String> roles;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        user = userService.read(2L);

        // Set up an Address object associated with the user
        address = new Address.Builder()
                .setId(1L)
                .setUser(user)
                .setTitle("Home")
                .setAddressLine1("Apt 101")
                .setAddressLine2("New York")
                .setCountry("South Africa")
                .setCity("Cape Town")
                .setPostalCode("9320")
                .setPhoneNumber( "1234567890")
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(null)
                .build();
    }

    @AfterEach
    void tearDown() {
       // addressRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testCreateAddress() {
        Address createdAddress = addressService.create(address);
        assertNotNull(createdAddress);
        assertEquals(address.getId(), createdAddress.getId());
    }

    @Test
    @Order(2)
    void testReadAddress() {
        Address createdAddress = addressService.create(address);
        Address readAddress = addressService.read(createdAddress.getId());
        assertNotNull(readAddress);
        assertEquals(createdAddress.getUser(), readAddress.getUser());
    }

    @Test
    @Order(3)
    void testUpdateAddress() {
        Address createdAddress = addressService.create(address);
        System.out.println("Created for test update by Id"+'\n'+createdAddress+'\n');
        createdAddress = new Address.Builder()
                .copy(createdAddress)
                .setAddressLine1("456 Elm St")
                .setCountry("Nigeria")
                .build();
        Address updatedAddress = addressService.update(createdAddress);
        System.out.println("Here is the updated Address"+ updatedAddress);
        assertNotNull(updatedAddress);
        assertEquals(createdAddress.getId(), updatedAddress.getId());
    }

    @Test
    @Order(4)
    void testFindAllAddresses() {
        addressService.create(address); // Create the address for testing
        List<Address> addresses = addressService.findAll();
        assertNotNull(addresses);
        assertEquals(1, addresses.size());
    }

    @Test
    @Order(5)
    void testFindByUser() {
        addressService.create(address); // Ensure address is created
        System.out.println("Created address"+ address);
        Optional<Address> addresses = addressService.findByUserId(1L);
        assertNotNull(addresses);
    }

    @Test
    @Order(6)
    void testFindByTitle() {
        addressService.create(address); // Ensure address is created
        List<Address> addresses = addressService.findByTitle("Home"); // Match the title used in setup
        assertNotNull(addresses);
        assertEquals(1, addresses.size());
    }
}
