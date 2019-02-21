package com.faizal.spring5rest.validator;

import com.faizal.spring5rest.api.v1.model.CustomerDTO;
import com.faizal.spring5rest.exceptions.NotValidException;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOValidator {

    public void validate(CustomerDTO customerDTO) {

        //someLogic to validate object

        throw new NotValidException("Testing Validator");

    }
}
