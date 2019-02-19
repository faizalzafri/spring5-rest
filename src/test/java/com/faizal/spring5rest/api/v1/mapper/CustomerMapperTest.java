package com.faizal.spring5rest.api.v1.mapper;

import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String NAME = "TEST_NAME";
    public static final long ID = 1L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setFirstname(NAME);
        customer.setId(ID);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(String.valueOf(NAME), customerDTO.getFirstname());

    }

    @Test
    public void customerDTOToCustomer() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);
        customerDTO.setId(ID);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(Long.valueOf(ID), customer.getId());
        assertEquals(String.valueOf(NAME), customer.getFirstname());

    }
}
