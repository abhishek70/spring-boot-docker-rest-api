package com.abhishekd.restapi.schedulers;

import com.abhishekd.restapi.repositories.CategoryRepository;
import com.abhishekd.restapi.repositories.CustomerRepository;
import com.abhishekd.restapi.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Method for deleting all record from all the tables
     * every 15 minutes
     */
    @Scheduled(fixedRate = 900000)
    public void databaseCleanUp() {

        //log.info("Clean up initiated");

        customerRepository.deleteAll();
        vendorRepository.deleteAll();

        //log.info("Customers Deleted => " + customerRepository.findAll().size());
        //log.info("Vendors Deleted => " + vendorRepository.findAll().size());
    }
}
