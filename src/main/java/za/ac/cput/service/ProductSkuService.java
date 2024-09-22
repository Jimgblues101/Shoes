package za.ac.cput.service;

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
        if (productSkuRepository.existsById(productSku.getId())) {
            return productSkuRepository.save(productSku);
        }
        return null;
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
