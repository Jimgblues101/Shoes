package za.ac.cput.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.ProductSku;
import za.ac.cput.repository.ProductSkuRepository;

import java.util.List;

/**
 * ProductSkuService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@Slf4j
@Service
@Transactional
public class ProductSkuService implements IProductSkuService {

    private final ProductSkuRepository productSkuRepository;

    @Autowired
    public ProductSkuService(ProductSkuRepository productSkuRepository) {
        this.productSkuRepository = productSkuRepository;
    }

    @Override
    public ProductSku create(ProductSku productSku) {
        return productSkuRepository.save(productSku);
    }

    @Override
    public ProductSku read(Long id) {
        return productSkuRepository.findById(id).orElse(null);
    }

    @Override
    public ProductSku update(ProductSku productSku) {
        ProductSku existingProductSku = productSkuRepository.findById(productSku.getId()).orElse(null);

        if (existingProductSku != null) {
            ProductSku updatedProductSku = new ProductSku.Builder()
                    .copy(existingProductSku)
                    .setId(existingProductSku.getId())
                    .setProduct(productSku.getProduct())
                    .setSizeAttribute(productSku.getSizeAttribute())
                    .setColorAttribute(productSku.getColorAttribute())
                    .setBrandAttribute(productSku.getBrandAttribute())
                    .setSku(productSku.getSku())
                    .setPrice(productSku.getPrice())
                    .setQuantity(productSku.getQuantity())
                    .setCreatedAt(productSku.getCreatedAt())
                    .setDeletedAt(productSku.getDeletedAt())
                    .build();
            return productSkuRepository.save(updatedProductSku);
        } else {
            log.warn("Attempt to update a non-existent product sku with ID: " + productSku.getId());
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        productSkuRepository.deleteById(id);
    }

    @Override
    public List<ProductSku> findAll() {
        return productSkuRepository.findAll();
    }
}
