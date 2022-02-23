package com.mehdi.rentcar.repository;

import com.mehdi.rentcar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
