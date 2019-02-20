package com.faizal.spring5rest.controllers.v1;

import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private static final String NAME = "TEST_NAME";

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCategories() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstname("Test_One");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);
        customer2.setFirstname("Test_Two");

        List<CustomerDTO> customerDTOList = Arrays.asList(customer, customer2);

        //when
        when(customerService.getAllCustomers()).thenReturn(customerDTOList);

        //then
        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void testGetCustomerByName() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);

        //when
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        //given
        CustomerDTO incomingDto = new CustomerDTO();
        incomingDto.setFirstname(NAME);
        incomingDto.setLastname(NAME);

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstname(NAME);
        returnDto.setLastname(NAME);
        returnDto.setUrl("/api/v1/customers/1");

        //when
        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);

        //then
        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(incomingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));

//        String response = mockMvc.perform(post("/api/v1/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(incomingDto)))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println("response" + response);
        // this assert statement will fail as url is null in incoming dto
        //assertEquals(new ObjectMapper().writeValueAsString(incomingDto), response);
    }

    @Test
    public void testCreateNewCustomer() throws Exception {

        //given
        CustomerDTO incomingDto = new CustomerDTO();
        incomingDto.setFirstname(NAME);
        incomingDto.setLastname(NAME);

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstname(NAME);
        returnDto.setLastname(NAME);
        returnDto.setUrl("/api/v1/customers/1");

        //when
        when(customerService.createNewCustomer(incomingDto)).thenReturn(returnDto);

        //then
        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(incomingDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));

//        String response = mockMvc.perform(post("/api/v1/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(incomingDto)))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println("response" + response);
        // this assert statement will fail as url is null in incoming dto
        //assertEquals(new ObjectMapper().writeValueAsString(incomingDto), response);
    }


}
