package com.faizal.spring5rest.bootstrap;

import com.faizal.spring5rest.domain.Category;
import com.faizal.spring5rest.domain.Customer;
import com.faizal.spring5rest.repositories.CategoryRepository;
import com.faizal.spring5rest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
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
