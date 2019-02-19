package com.faizal.spring5rest.controllers.v1;

import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.api.v1.model.CustomerListDTO;
import com.faizal.spring5rest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCategories() {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable Long id) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);

    }

}
