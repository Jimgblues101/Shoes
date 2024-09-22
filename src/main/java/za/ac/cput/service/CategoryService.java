package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category read(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category) {
        if (category.getId() == null || !categoryRepository.existsById(category.getId())) {
            throw new IllegalArgumentException("Category with the given ID does not exist.");
        }
        // Using Builder pattern directly for updating the entity
        Category updatedCategory = new Category.Builder()
                .setId(category.getId())  // Reusing the existing ID
                .setName(category.getName())
                .setDescription(category.getDescription())
                .setCreatedAt(category.getCreatedAt())
                .setDeletedAt(category.getDeletedAt())
                .build();

        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findByCreatedAtAfter(LocalDateTime createdAt) {
        return categoryRepository.findByCreatedAtAfter(createdAt);
    }

    @Override
    public List<Category> findByDeletedAt(LocalDateTime deletedAt) {
        return categoryRepository.findByDeletedAt(deletedAt);
    }

    @Override
    public List<Category> findByNameContaining(String keyword) {
        return categoryRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Category> findByDescriptionContaining(String keyword) {
        return categoryRepository.findByDescriptionContaining(keyword);
    }

    @Override
    public List<Category> findCategoriesCreatedWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return categoryRepository.findCategoriesCreatedWithinDateRange(startDate, endDate);
    }

    @Override
    public Category findMostRecentlyCreatedCategory() {
        return categoryRepository.findMostRecentlyCreatedCategory();
    }

    @Override
    public Optional<Category> findByDeletedAtIsNotNull() {
        return categoryRepository.findByDeletedAtIsNotNull();
    }
}
