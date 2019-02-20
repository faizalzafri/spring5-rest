package com.faizal.spring5rest.services;

import com.faizal.spring5rest.api.v1.mapper.CustomerMapper;
import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.domain.Customer;
import com.faizal.spring5rest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final String NAME = "TEST_NAME";
    private static final Long ID = 1L;

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() throws Exception {

        //given
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        //when
        List<CustomerDTO> returnList = customerService.getAllCustomers();

        //then
        assertEquals(customerList.size(), returnList.size());
    }

    @Test
    public void getCustomerById() throws Exception {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        //then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstname());

    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(ID);
        savedCustomer.setFirstname(customerDTO.getFirstname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getUrl());
    }

    @Test
    public void updateCustomerTest() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(ID);
        savedCustomer.setFirstname(customerDTO.getFirstname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.updateCustomer(ID, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getUrl());
    }
}
