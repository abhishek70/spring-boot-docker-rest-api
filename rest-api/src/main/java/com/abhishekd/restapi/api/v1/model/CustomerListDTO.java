package com.abhishekd.restapi.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Customer list data transfer object model
 */
@ApiModel(value = "Customer List", description = "customer list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customers;
}
