package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ProductSku;
import za.ac.cput.service.ProductSkuService;

import java.util.List;

/**
 * ProductSkuController.java
 *
 * This class handles HTTP requests related to product SKUs.
 * It provides endpoints for CRUD operations on product SKU items.
 *
 * Author: Rethabile Ntsekhe
 * Date: 25-Aug-24
 */
@RestController
@RequestMapping("/api/product-skus")
public class ProductSkuController {

    private final ProductSkuService productSkuService;

    @Autowired
    public ProductSkuController(ProductSkuService productSkuService) {
        this.productSkuService = productSkuService;
    }

    /**
     * Creates a new product SKU.
     *
     * @param productSku the product SKU to be created
     * @return ResponseEntity containing the created ProductSku and HTTP status code
     */
    @PostMapping
    public ResponseEntity<ProductSku> createProductSku(@RequestBody ProductSku productSku) {
        ProductSku createdProductSku = productSkuService.create(productSku);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductSku);
    }

    /**
     * Retrieves a product SKU by its ID.
     *
     * @param id the ID of the product SKU to retrieve
     * @return ResponseEntity containing the ProductSku if found, or a 404 Not Found status if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductSku> getProductSkuById(@PathVariable Long id) {
        ProductSku productSku = productSkuService.read(id);
        if (productSku != null) {
            return ResponseEntity.ok(productSku);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing product SKU.
     *
     * @param id the ID of the product SKU to be updated
     * @param productSku the updated product SKU details
     * @return ResponseEntity containing the updated ProductSku and HTTP status code, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductSku> updateProductSku(@PathVariable Long id, @RequestBody ProductSku productSku) {
        ProductSku updatedProductSku = productSkuService.update(productSku);
        if (updatedProductSku != null) {
            return ResponseEntity.ok(updatedProductSku);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a product SKU by its ID.
     *
     * @param id the ID of the product SKU to delete
     * @return ResponseEntity with HTTP status code indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductSku(@PathVariable Long id) {
        productSkuService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all product SKUs.
     *
     * @return ResponseEntity containing the list of all ProductSkus and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<ProductSku>> getAllProductSkus() {
        List<ProductSku> productSkus = productSkuService.findAll();
        return ResponseEntity.ok(productSkus);
    }
}
