package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.domain.Category;

import java.util.List;

/**
 * Category Interface
 */
public interface CategoryService {

    List<CategoryDTO> getCategories();
    CategoryDTO findByCategoryName(String categoryName);
}
