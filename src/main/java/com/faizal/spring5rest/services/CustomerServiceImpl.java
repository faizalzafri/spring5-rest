package com.faizal.spring5rest.services;

import com.faizal.spring5rest.api.v1.mapper.CustomerMapper;
import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.domain.Customer;
import com.faizal.spring5rest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer1 = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer1);
        returnDto.setUrl("/api/v1/customers/" + savedCustomer1.getId());
        return returnDto;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerDTO savedDto = saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
        if (savedDto.getId().longValue() == id)
            System.err.println("Updating customer");
        return savedDto;
    }
}
