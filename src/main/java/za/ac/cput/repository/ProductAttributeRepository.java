package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ProductAttribute;

import java.util.Optional;

/**
 * Repository interface for {@link ProductAttribute} entity.
 * Provides methods to perform CRUD operations on ProductAttribute entities.
 *
 * @autor Rethabile Ntsekhe
 * @date 25-Aug-24
 */


@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
    Optional<ProductAttribute> findById(Long id);
}
