package com.abhishekd.restapi.controllers.v1;

import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.domain.Category;
import com.abhishekd.restapi.exceptions.RestResponseEntityExceptionHandler;
import com.abhishekd.restapi.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetCategories() throws Exception {

        // Given
        CategoryDTO categoryDTOOne = new CategoryDTO();
        CategoryDTO categoryDTOTwo = new CategoryDTO();

        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
        categoryDTOList.add(categoryDTOOne);
        categoryDTOList.add(categoryDTOTwo);

        // When
        when(categoryService.getCategories()).thenReturn(categoryDTOList);

        // Then
        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));


    }

    @Test
    public void testGetCategoryByName() throws Exception {

        // Given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("Fruits");

        // When
        when(categoryService.findByCategoryName("Fruits")).thenReturn(categoryDTO);

        // Then
        mockMvc.perform(get("/api/v1/categories/Fruits")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", equalTo("Fruits")));

    }
}