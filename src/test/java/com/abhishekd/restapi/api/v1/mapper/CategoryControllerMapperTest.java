package com.abhishekd.restapi.api.v1.mapper;

import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryControllerMapperTest {

    private static final String DRIED = "Dried";
    private static final String FRUITS = "Fruits";
    private static final long L = 2L;
    private static final long L1 = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {

        // Given
        Category category = new Category();
        category.setCategoryName(FRUITS);
        category.setCategoryId(L1);

        // When
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // Then
        assertEquals(categoryDTO.getCategoryName(), FRUITS);

    }

    @Test
    public void categoryDTOToCategory() {

        // Given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(DRIED);

        // When
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        // Then
        assertEquals(category.getCategoryName(), DRIED);

    }
}