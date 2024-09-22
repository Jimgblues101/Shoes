package za.ac.cput.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.cput.domain.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ICategoryService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

public interface ICategoryService extends IService<Category, Long> {
    /**
     * Finds all categories with a specific name.
     *
     * @param name the name of the category to search by
     * @return a list of categories with the given name
     */
    List<Category> findByName(String name);
    /**
     * Finds all categories created after a certain date.
     *
     * @param createdAt the date to search by
     * @return a list of categories created after the given date
     */
    List<Category> findByCreatedAtAfter(LocalDateTime createdAt);

    /**
     * Finds all categories that have been marked as deleted.
     *
     * @param deletedAt the date to search by
     * @return a list of categories marked as deleted on or after the given date
     */
    List<Category> findByDeletedAt(LocalDateTime deletedAt);

    /**
     * Finds all categories with a name containing a specific keyword.
     *
     * @param keyword the keyword to search for in category names
     * @return a list of categories with names containing the keyword
     */
    List<Category> findByNameContaining(String keyword);
    /**
     * Finds all categories with a description containing a specific keyword.
     *
     * @param keyword the keyword to search for in category descriptions
     * @return a list of categories with descriptions containing the keyword
     */
    List<Category> findByDescriptionContaining(String keyword);

    /**
     * Finds all categories created within a specific date range using JPQL.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a list of categories created within the date range
     */
    List<Category> findCategoriesCreatedWithinDateRange(@Param("startDate") LocalDateTime startDate,
                                                        @Param("endDate") LocalDateTime endDate);

    /**
     * Finds the most recently created category using JPQL.
     *
     * @return the most recently created category
     */
    Category findMostRecentlyCreatedCategory();

    /**
     * Finds all categories that have been deleted.
     *
     * @return a list of categories that have been marked as deleted
     */
    Optional<Category> findByDeletedAtIsNotNull();
}
