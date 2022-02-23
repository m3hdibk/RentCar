package com.mehdi.rentcar.service.impl;

import com.mehdi.rentcar.exception.InputValidationException;
import com.mehdi.rentcar.mapper.Mapper;
import com.mehdi.rentcar.model.Customer;
import com.mehdi.rentcar.model.dto.CustomerData;
import com.mehdi.rentcar.repository.CustomerRepository;
import com.mehdi.rentcar.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Mapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Mapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public void createCustomer(CustomerData customerData) {
        customerRepository.save(mapper.customerDataToEntity(customerData));
    }

    @Override
    public void updateCustomer(CustomerData customerData) {
        Optional<Customer> customerOptional = customerRepository.findById(customerData.getId());
        Customer customer = customerOptional
                .orElseThrow(() -> new InputValidationException(List.of("Invalid Customer Id")));
        customer.setFullName(customerData.getFullName());
        customer.setPhone(customerData.getPhone());
        customer.setAddress(customerData.getAddress());
        customer.setEmail(customerData.getEmail());
        customer.setIdNumber(customerData.getIdNumber());
        customer.setNote(customerData.getNote());
        customerRepository.save(customer);
    }
}
