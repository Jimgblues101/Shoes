package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductAttribute;
import za.ac.cput.domain.ProductSku;
import za.ac.cput.enums.ProductAttributeType;
import za.ac.cput.factory.ProductAttributeFactory;
import za.ac.cput.factory.ProductSkuFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ProductSkuServiceTest {
    @Autowired
    private ProductSkuService productSkuService;
    private ProductSku productSku;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductAttributeService productAttributeService;

    @BeforeEach
    void setUp() {
        Product product = productService.read(59L);

        // Set up Product attributes
        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null
        );

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null
        );

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null
        );

        ProductAttribute size = productAttributeService.create(sizeAttribute);
        ProductAttribute color = productAttributeService.create(colorAttribute);
        ProductAttribute brand = productAttributeService.create(brandAttribute);

        // Create Product SKU
        productSku = ProductSkuFactory.createProductSku(
                null,
                product,
                size,
                color,
                brand,
                "SKU-123",
                100.0,
                10,
                LocalDateTime.now(),
                null
        );

        productSku =productSkuService.create(productSku);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void create() {
        productSku = productSkuService.create(productSku);
        assertNotNull(productSku);
        System.out.println("Created: " + productSku);

    }

    @Test
    @Order(2)
    void read() {
    }

    @Test
    @Order(3)
    void update() {
    }

    @Test
    @Order(4)
    void findAll() {
    }

    @Test
    @Order(5)
    void delete() {
    }
}