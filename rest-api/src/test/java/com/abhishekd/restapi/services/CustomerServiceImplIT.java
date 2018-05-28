package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.mapper.CustomerMapper;
import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.bootstrap.Bootstrap;
import com.abhishekd.restapi.domain.Customer;
import com.abhishekd.restapi.repositories.CategoryRepository;
import com.abhishekd.restapi.repositories.CustomerRepository;
import com.abhishekd.restapi.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

        // Load the default data set for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }


    @Test
    public void testPatchCustomer() throws Exception {

        Long customerId = getCustomerId();

        Customer savedCustomer = customerRepository.getCustomerByCustomerId(customerId);
        String firstName = savedCustomer.getFirstName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("TestUpdatedName");

        customerService.patchCustomer(customerId, customerDTO);

        Customer getUpdatedCustomer = customerRepository.getCustomerByCustomerId(customerId);

        assertNotNull(getUpdatedCustomer);
        assertEquals(getUpdatedCustomer.getFirstName(), "TestUpdatedName");


    }

    /**
     * Get first customer Id for testing
     * @return
     */
    private Long getCustomerId() {

        List<Customer> customers = customerRepository.findAll();

        return customers.get(0).getCustomerId();
    }



}
