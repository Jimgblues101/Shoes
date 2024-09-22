package za.ac.cput.service;

import za.ac.cput.domain.SubCategory;

/**
 * ISubCategoryService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */

public interface ISubCategoryService extends IService<SubCategory, Long>{

    SubCategory findById(Long id);
}
