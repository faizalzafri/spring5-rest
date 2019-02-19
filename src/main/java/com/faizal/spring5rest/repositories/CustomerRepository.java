package com.faizal.spring5rest.repositories;

import com.faizal.spring5rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
