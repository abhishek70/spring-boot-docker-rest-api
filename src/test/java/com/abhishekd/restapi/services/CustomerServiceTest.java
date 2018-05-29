package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.mapper.CustomerMapper;
import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.domain.Customer;
import com.abhishekd.restapi.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getCustomers() {

        // Given
        List<Customer> customerList = new ArrayList<Customer>();

        Customer customerOne = new Customer();
        Customer customerTwo = new Customer();
        customerList.add(customerOne);
        customerList.add(customerTwo);

        // When
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.getCustomers();

        // Then
        assertEquals(customerDTOList.size(), customerList.size());

    }

    @Test
    public void testGetCustomerById() {

        // Given
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("first");
        customer.setLastName("last");

        // When
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        // Then
        assertEquals(customerDTO.getFirstName(), "first");
    }

    @Test
    public void testCreateCustomer() {

        // Given
       CustomerDTO customerDTO = new CustomerDTO();

       customerDTO.setLastName("Smith");
       customerDTO.setLastName("Rob");

       Customer customer = new Customer();
       customer.setFirstName(customerDTO.getFirstName());
       customer.setLastName(customerDTO.getLastName());
       customer.setCustomerId(1L);

       when(customerRepository.save(any(Customer.class))).thenReturn(customer);

       // When
       CustomerDTO saveCustomer = customerService.createCustomer(customerDTO);

       // Then
        assertEquals(saveCustomer.getFirstName(), customerDTO.getFirstName());
        assertEquals("/api/v1/customers/1", saveCustomer.getCustomerUrl());

    }

    @Test
    public void testUpdateCustomer() throws Exception {

        // Given
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setLastName("Smith");
        customerDTO.setLastName("Rob");

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setCustomerId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // When
        CustomerDTO returnCustomerDto = customerService.updateCustomer(1L, customerDTO);

        // Then
        assertEquals(returnCustomerDto.getFirstName(), customerDTO.getFirstName());
        assertEquals(returnCustomerDto.getCustomerUrl(), "/api/v1/customers/1");

    }

    @Test
    public void testdeleteCustomerById() throws Exception{

        Long customerId = 1L;

        customerRepository.deleteById(customerId);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}