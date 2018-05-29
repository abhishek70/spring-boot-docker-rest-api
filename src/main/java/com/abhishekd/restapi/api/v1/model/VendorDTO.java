package com.abhishekd.restapi.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Vendor data transfer object model
 */
@ApiModel(value = "Vendor", description = "vendor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "Vendor Name", required = true, allowEmptyValue = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String vendorName;

    @ApiModelProperty(value = "Only available in response", readOnly = true)
    private String vendorUrl;

}
