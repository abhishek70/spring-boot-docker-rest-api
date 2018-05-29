package com.abhishekd.restapi.api.v1.mapper;

import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    public static final long L = 1L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        // Given
        Customer customer = new Customer();
        customer.setCustomerId(L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        // When
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // Then
        assertEquals(customerDTO.getFirstName(), FIRST_NAME);
        assertEquals(customerDTO.getLastName(), LAST_NAME);


    }

    @Test
    public void customerDTOToCustomer() {

        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        // When
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        // Then
        assertEquals(customer.getFirstName(), FIRST_NAME);
        assertEquals(customer.getLastName(), LAST_NAME);

    }
}