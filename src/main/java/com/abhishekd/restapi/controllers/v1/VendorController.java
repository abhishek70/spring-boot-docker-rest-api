package com.abhishekd.restapi.controllers.v1;

import com.abhishekd.restapi.api.v1.model.VendorDTO;
import com.abhishekd.restapi.api.v1.model.VendorListDTO;
import com.abhishekd.restapi.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Vendor
 * GET POST PUT PATCH DELETE HTTP Methods are supported currently
 */
@Api(description = "Supports GET, POST, PATCH, PUT, DELETE operations", tags = {"vendors"})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    /**
     * Vendor Controller Base URL
     */
    public static final String BASE_URL = "/api/v1/vendors";

    /**
     * Inject dependencies
     */
    @Autowired
    private VendorService vendorService;

    /**
     * Constructor
     * @param vendorService
     */
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Method for getting all vendors
     * @return
     */
    @ApiOperation(value = "Lists all the vendors", notes = "")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendors() {

        return new VendorListDTO(vendorService.getVendors());
    }

    /**
     * Method for getting vendor by vendor Id
     * @param vendorId
     * @return
     */
    @ApiOperation(value = "Get a vendor by id", notes = "")
    @GetMapping(value = {"/{vendorId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long vendorId) {

        return vendorService.getVendorById(vendorId);
    }

    /**
     * Method for creating new vendor
     * @param vendorDTO
     * @return
     */
    @ApiOperation(value = "Create a vendor", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
    }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@Validated @RequestBody VendorDTO vendorDTO) {

        return vendorService.createVendor(vendorDTO);
    }

    /**
     * Method for updating a vendor
     * @param vendorId
     * @param vendorDTO
     * @return
     */
    @ApiOperation(value = "Replace a vendor by new data", notes = "")
    @PutMapping(value = {"/{vendorId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long vendorId,@Validated @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(vendorId, vendorDTO);
    }

    /**
     * Method for partially updating a vendor
     * @param vendorId
     * @param vendorDTO
     * @return
     */
    @ApiOperation(value = "Update a vendor", notes = "")
    @PatchMapping(value = {"/{vendorId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long vendorId,@Validated @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(vendorId, vendorDTO);
    }

    /**
     * Method for deleting vendor
     * @param vendorId
     */
    @ApiOperation(value = "Delete a vendor", notes = "")
    @DeleteMapping(value = {"/{vendorId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long vendorId) {

        vendorService.deleteVendorById(vendorId);

    }
}
