package com.faizal.spring5rest.bootstrap;

import com.faizal.spring5rest.domain.Category;
import com.faizal.spring5rest.domain.Customer;
import com.faizal.spring5rest.domain.Vendor;
import com.faizal.spring5rest.repositories.CategoryRepository;
import com.faizal.spring5rest.repositories.CustomerRepository;
import com.faizal.spring5rest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

    }

    private void loadCustomers() {
        Customer tony = new Customer();
        tony.setFirstname("Tony");
        tony.setLastname("Stark");

        Customer steve = new Customer();
        steve.setFirstname("Steve");
        steve.setLastname("Rogers");

        Customer natasha = new Customer();
        natasha.setFirstname("Natasha");
        natasha.setLastname("Romanoff");

        customerRepository.save(tony);
        customerRepository.save(steve);
        customerRepository.save(natasha);

        System.out.println("Customer Data Loaded = " + customerRepository.count());


    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Category Data Loaded = " + categoryRepository.count());
    }
}
