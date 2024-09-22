package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.enums.ProductAttributeType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductSkuFactoryTest {

    private List<SubCategory> subCategoryList;
    private Category category;

    @BeforeEach
    void setUp() {
        // Create a sample Category
        category = CategoryFactory.createCategory(
                1L,
                "LowTops",
                "description",
                LocalDateTime.of(2024, 6, 12, 0, 0),
                null);

        // Create a sample SubCategory
        SubCategory subCategory = SubCategoryFactory.createSubCategory(
                1L,
                category,
                "Air Jordans",
                "description",
                LocalDateTime.of(2024, 6, 12, 0, 0),
                null);

        // Store the SubCategory in a List
        subCategoryList = Collections.singletonList(subCategory);  // or Arrays.asList if you have multiple subcategories
    }

    @Test
    void testCreateProductSku() {
        // Create sample ProductAttribute objects for size, color, and brand
        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null);

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null);

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null);

        // Create a sample Product object
        Product product = ProductFactory.createProduct(
                null,
                "Product Name",
                "Product Description",
                "Product Summary",
                "Product Cover",
                null,
                subCategoryList,
                LocalDateTime.now(),
                null);

        // Create a sample ProductSku object
        ProductSku productSku = ProductSkuFactory.createProductSku(
                null,
                product,
                sizeAttribute,
                colorAttribute,
                brandAttribute,
                "SKU-123",
                100.0,
                10,
                LocalDateTime.now(),
                null);

        // Verify that the ProductSku object is not null
        assertNotNull(productSku);

        // Print the created ProductSku object to the terminal
        System.out.println("Created ProductSku: " + productSku);
    }

    @Test
    void testCreateProductSku_WithNullProduct_ThrowsIllegalArgumentException() {
        // Create sample ProductAttribute objects for size, color, and brand
        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null);

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null);

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null);

        // Try to create a ProductSku object with a null product
        assertThrows(IllegalArgumentException.class,
                () -> ProductSkuFactory.createProductSku(
                        null,
                        null,
                        sizeAttribute,
                        colorAttribute,
                        brandAttribute,
                        "SKU-123",
                        100.0,
                        10,
                        LocalDateTime.now(),
                        null));

        System.out.println("Expected IllegalArgumentException thrown when creating ProductSku with null product");
    }

    @Test
    void testCreateProductSku_WithNullSku_ThrowsIllegalArgumentException() {
        // Create sample ProductAttribute objects for size, color, and brand
        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null);

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null);

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null);

        // Create a sample Product object
        Product product = ProductFactory.createProduct(
                null,
                "Product Name",
                "Product Description",
                "Product Summary",
                "Product Cover",
                null,
                subCategoryList,  // Passing the list of SubCategory
                LocalDateTime.now(),
                null);

        // Try to create a ProductSku object with a null SKU
        assertThrows(IllegalArgumentException.class,
                () -> ProductSkuFactory.createProductSku(
                        null,
                        product,
                        sizeAttribute,
                        colorAttribute,
                        brandAttribute,
                        null,
                        100.0,
                        10,
                        LocalDateTime.now(),
                        null));

        System.out.println("Expected IllegalArgumentException thrown when creating ProductSku with null SKU");
    }

    @Test
    void testCreateProductSku_WithZeroQuantity_ThrowsIllegalArgumentException() {
        // Create sample ProductAttribute objects for size, color, and brand
        ProductAttribute sizeAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.SIZE,
                "10",
                LocalDateTime.now(),
                null);

        ProductAttribute colorAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.COLOR,
                "Green",
                LocalDateTime.now(),
                null);

        ProductAttribute brandAttribute = ProductAttributeFactory.createProductAttribute(
                null,
                ProductAttributeType.BRAND,
                "Nike",
                LocalDateTime.now(),
                null);

        // Create a sample Product object
        Product product = ProductFactory.createProduct(
                null,
                "Product Name",
                "Product Description",
                "Product Summary",
                "Product Cover",
                null,
                subCategoryList,  // Passing the list of SubCategory
                LocalDateTime.now(),
                null);

        // Try to create a ProductSku object with a zero quantity
        assertThrows(IllegalArgumentException.class,
                () -> ProductSkuFactory.createProductSku(
                        null,
                        product,
                        sizeAttribute,
                        colorAttribute,
                        brandAttribute,
                        "SKU-123",
                        100.0,
                        0,
                        LocalDateTime.now(),
                        null));

        System.out.println("Expected IllegalArgumentException thrown when creating ProductSku with zero quantity");
    }
}
