package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.mapper.CategoryMapper;
import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.domain.Category;
import com.abhishekd.restapi.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoryControllerServiceTest {

    public static final String CATEGORY_NAME = "Fruits";
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getCategories() {

        // Given
        List<Category> categoryList = new ArrayList<Category>();

        Category one = new Category();
        Category two = new Category();

        categoryList.add(one);
        categoryList.add(two);

        // When
        when(categoryRepository.findAll()).thenReturn(categoryList);


        List<CategoryDTO> categoryDTOList = categoryService.getCategories();

        // Then
        assertEquals(categoryList.size(), categoryDTOList.size());
    }

    @Test
    public void findByCategoryName() {

        // Given
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName(CATEGORY_NAME);

        // When
        when(categoryRepository.findByCategoryName(CATEGORY_NAME)).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.findByCategoryName(CATEGORY_NAME);

        // Then
        assertEquals(categoryDTO.getCategoryName(), CATEGORY_NAME);
    }
}