package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.SubCategory;
import za.ac.cput.repository.SubCategoryRepository;

import java.util.List;

/**
 * SubCategoryService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */
@Service
@Transactional
public class SubCategoryService implements ISubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory create(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory read(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public SubCategory update(SubCategory subCategory) {
        if (subCategoryRepository.existsById(subCategory.getId())) {
            return subCategoryRepository.save(subCategory);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        subCategoryRepository.deleteById(id);
    }

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory findById(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }
}
