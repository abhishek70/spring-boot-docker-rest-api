package com.abhishekd.restapi.controllers.v1;

import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.api.v1.model.CustomerListDTO;
import com.abhishekd.restapi.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Customer
 * GET POST PUT PATCH DELETE HTTP Methods are supported currently
 */
@Api(description ="Supports GET, POST, PATCH, PUT, DELETE operations", tags = {"customers"})
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    /**
     * Customer Controller Base URL
     */
    public static final String BASE_URL = "/api/v1/customers";

    /**
     * Inject dependencies
     */
    @Autowired
    private CustomerService customerService;

    /**
     * Constructor
     * @param customerService
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Method for getting all customers
     * @return
     */
    @ApiOperation(value = "Lists all the customers", notes = "")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getCustomers() {

        return new CustomerListDTO(customerService.getCustomers());
    }

    /**
     * Method for getting customer by customer Id
     * @param customerId
     * @return
     */
    @ApiOperation(value = "Get a customer by id", notes = "")
    @GetMapping(value = {"/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long customerId) {

        return customerService.getCustomerById(customerId);
    }

    /**
     * Method for creating new customer
     * @param customerDTO
     * @return
     */
    @ApiOperation(value = "Create a customer", notes = "")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {

        return customerService.createCustomer(customerDTO);
    }

    /**
     * Method for updating a customer
     * @param customerId
     * @param customerDTO
     * @return
     */
    @ApiOperation(value = "Replace a customer by new data", notes = "")
    @PutMapping(value = {"/{customerId}"},produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO);
    }

    /**
     * Method for partially updating a customer
     * @param customerId
     * @param customerDTO
     * @return
     */
    @ApiOperation(value = "Update a customer", notes = "")
    @PatchMapping(value = {"/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long customerId,@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(customerId, customerDTO);
    }

    /**
     * Method for deleting customer
     * @param customerId
     */
    @ApiOperation(value = "Delete a customer", notes = "")
    @DeleteMapping(value = {"/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long customerId) {

        customerService.deleteCustomerById(customerId);

    }
}
