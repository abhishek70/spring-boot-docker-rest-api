package com.abhishekd.restapi.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Category list data transfer object model
 */
@ApiModel(value = "Category List", description = "category list")
@Data
@AllArgsConstructor
public class CategoryListDTO {

    List<CategoryDTO> categories;
}
