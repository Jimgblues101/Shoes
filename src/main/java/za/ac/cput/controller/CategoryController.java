package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.service.CategoryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * CategoryController.java
 *
 * This class handles HTTP requests related to categories.
 * It provides endpoints for CRUD operations and custom queries on categories.
 *
 * Author: Rethabile Ntsekhe
 * Date: 25-Aug-24
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Creates a new category.
     *
     * @param category the category to be created
     * @return ResponseEntity containing the created Category and HTTP status code
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return ResponseEntity containing the Category if found, or a 404 Not Found status if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.read(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to be updated
     * @param category the updated category details
     * @return ResponseEntity containing the updated Category and HTTP status code, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return ResponseEntity with HTTP status code indicating success or failure
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all categories.
     *
     * @return ResponseEntity containing the list of all Categories and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }

    // New endpoints for custom queries

    /**
     * Retrieves categories by name.
     *
     * @param name the name of the category to search for
     * @return ResponseEntity containing the list of Categories matching the name and HTTP status code
     */
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Category>> findByName(@PathVariable String name) {
        List<Category> categories = categoryService.findByName(name);
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves categories created after a specified date.
     *
     * @param date the date to search for categories created after
     * @return ResponseEntity containing the list of Categories and HTTP status code
     */
    @GetMapping("/search/created-after/{date}")
    public ResponseEntity<List<Category>> findByCreatedAtAfter(@PathVariable LocalDateTime date) {
        List<Category> categories = categoryService.findByCreatedAtAfter(date);
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves categories that were deleted.
     *
     * @return ResponseEntity containing the list of deleted Categories and HTTP status code
     */
    @GetMapping("/deleted")
    public ResponseEntity<Optional<Category>> findAllDeletedCategories() {
        Optional<Category> categories = categoryService.findByDeletedAtIsNotNull();
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves categories whose names contain a specific string.
     *
     * @param keyword the keyword to search for in category names
     * @return ResponseEntity containing the list of matching Categories and HTTP status code
     */
    @GetMapping("/search/name-contains/{keyword}")
    public ResponseEntity<List<Category>> findByNameContaining(@PathVariable String keyword) {
        List<Category> categories = categoryService.findByNameContaining(keyword);
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves categories whose descriptions contain a specific string.
     *
     * @param keyword the keyword to search for in category descriptions
     * @return ResponseEntity containing the list of matching Categories and HTTP status code
     */
    @GetMapping("/search/description-contains/{keyword}")
    public ResponseEntity<List<Category>> findByDescriptionContaining(@PathVariable String keyword) {
        List<Category> categories = categoryService.findByDescriptionContaining(keyword);
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves categories created within a specific date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return ResponseEntity containing the list of Categories created within the date range and HTTP status code
     */
    @GetMapping("/search/created-between")
    public ResponseEntity<List<Category>> findCategoriesCreatedWithinDateRange(
            @RequestParam("start") LocalDateTime startDate,
            @RequestParam("end") LocalDateTime endDate) {
        List<Category> categories = categoryService.findCategoriesCreatedWithinDateRange(startDate, endDate);
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves the most recently created category.
     *
     * @return ResponseEntity containing the most recently created Category and HTTP status code
     */
    @GetMapping("/recent")
    public ResponseEntity<Category> findMostRecentlyCreatedCategory() {
        Category category = categoryService.findMostRecentlyCreatedCategory();
        return ResponseEntity.ok(category);
    }
}
