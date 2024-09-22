package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.ProductAttribute;
import za.ac.cput.repository.ProductAttributeRepository;

import java.util.List;

/**
 * ProductAttributeService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

@Service
@Transactional
public class ProductAttributeService implements IProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public ProductAttributeService(ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    @Override
    public ProductAttribute create(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public ProductAttribute read(Long id) {
        return productAttributeRepository.findById(id).orElse(null);
    }

    @Override
    public ProductAttribute update(ProductAttribute productAttribute) {
        if(productAttribute == null || !productAttributeRepository.existsById(productAttribute.getId())) {
            throw new IllegalArgumentException("Cart with the given ID does not exist.");
        }

        if (productAttributeRepository.existsById(productAttribute.getId())) {
            return productAttributeRepository.save(productAttribute);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productAttributeRepository.deleteById(id);
    }

    @Override
    public List<ProductAttribute> findAll() {
        return productAttributeRepository.findAll();
    }
}
