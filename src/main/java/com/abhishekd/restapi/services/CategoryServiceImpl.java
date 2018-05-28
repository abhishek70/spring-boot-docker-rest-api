package com.abhishekd.restapi.services;


import com.abhishekd.restapi.api.v1.mapper.CategoryMapper;
import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Category service implementation class
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * Injecting dependencies
     */
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;


    /**
     * Constructor
     * @param categoryMapper
     * @param categoryRepository
     */
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Method implementation for getting all categories from database
     * @return
     */
    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method implementation for finding a category by provided category name
     * @param categoryName
     * @return
     */
    @Override
    public CategoryDTO findByCategoryName(String categoryName) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByCategoryName(categoryName));
    }
}
