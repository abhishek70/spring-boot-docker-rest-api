package com.abhishekd.restapi.bootstrap;

import com.abhishekd.restapi.domain.Category;
import com.abhishekd.restapi.domain.Customer;
import com.abhishekd.restapi.domain.Vendor;
import com.abhishekd.restapi.repositories.CategoryRepository;
import com.abhishekd.restapi.repositories.CustomerRepository;
import com.abhishekd.restapi.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Bootstrap class to load default data in database on startup
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository   = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Load Categories
        addCategories();

        // Load Customers
        addCustomer();

        // Load Vendors
        addVendors();

    }

    /**
     * Load categories in db
     */
    private void addCategories() {

        categoryRepository.deleteAll();

        Category fruits = new Category();
        fruits.setCategoryName("Fruits");

        Category dried = new Category();
        dried.setCategoryName("Dried");

        Category fresh = new Category();
        fresh.setCategoryName("Fresh");

        Category exotic = new Category();
        exotic.setCategoryName("Exotic");

        Category nuts = new Category();
        nuts.setCategoryName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        //System.out.println("Categories data loaded in db " + categoryRepository.count());
    }

    /**
     * Load customers in db
     */
    private void addCustomer() {

        customerRepository.deleteAll();

        Customer one = new Customer();
        one.setFirstName("Alice");
        one.setLastName("Eastman");

        Customer two = new Customer();
        two.setFirstName("Freddy");
        two.setLastName("Meyers");

        Customer three = new Customer();
        three.setFirstName("Freddy");
        three.setLastName("xxxeyers");

        Customer four = new Customer();
        four.setFirstName("Sam");
        four.setLastName("Axe");

        Customer five = new Customer();
        five.setFirstName("Joe");
        five.setLastName("Buck");

        customerRepository.save(one);
        customerRepository.save(two);
        customerRepository.save(three);
        customerRepository.save(four);
        customerRepository.save(five);

        //System.out.println("Customers data loaded in db " + customerRepository.count());
    }

    /**
     * Load categories in db
     */
    private void addVendors() {

        vendorRepository.deleteAll();

        Vendor vendorOne = new Vendor();
        vendorOne.setVendorName("Western Tasty Fruits Ltd.");
        vendorRepository.save(vendorOne);

        Vendor vendorTwo = new Vendor();
        vendorTwo.setVendorName("Exotic Fruits Company");
        vendorRepository.save(vendorTwo);

        Vendor vendorThree = new Vendor();
        vendorThree.setVendorName("Home Fruits");
        vendorRepository.save(vendorThree);

        Vendor vendorFour = new Vendor();
        vendorFour.setVendorName("Fun Fresh Fruits Ltd.");
        vendorRepository.save(vendorFour);

        Vendor vendorFive = new Vendor();
        vendorFive.setVendorName("Nuts for Nuts Company");
        vendorRepository.save(vendorFive);

        //System.out.println("Vendors data loaded in db " + vendorRepository.count());
    }
}
