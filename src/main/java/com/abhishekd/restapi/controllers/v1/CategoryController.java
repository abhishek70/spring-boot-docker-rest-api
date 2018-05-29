package com.abhishekd.restapi.controllers.v1;

import com.abhishekd.restapi.api.v1.model.CategoryDTO;
import com.abhishekd.restapi.api.v1.model.CategoryListDTO;
import com.abhishekd.restapi.exceptions.ResourceNotFoundException;
import com.abhishekd.restapi.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Category
 * GET
 */
@Api(description = "Supports GET operation", tags = {"categories"})
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    /**
     * Category Controller Base URL
     */
    public static final String BASE_URL = "/api/v1/categories";

    /**
     * Inject dependencies
     */
    private CategoryService categoryService;

    /**
     * Constructor
     * @param categoryService
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Method for getting all categories
     * @return
     */
    @ApiOperation(value = "Lists all the product categories", notes = "")
    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories() {

        return new CategoryListDTO(categoryService.getCategories());

    }

    /**
     * Method for getting category by category name
     * @param categoryName
     * @return
     */
    @ApiOperation(value = "Get a category by category name", notes = "", response = CategoryDTO.class)
    @GetMapping(value = {"/{categoryName}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String categoryName) {

        CategoryDTO categoryDTO = categoryService.findByCategoryName(categoryName);

        if(categoryDTO == null) {
            throw new ResourceNotFoundException();
        }

        return categoryDTO;
    }

}
