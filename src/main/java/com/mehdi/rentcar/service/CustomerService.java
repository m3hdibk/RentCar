package com.mehdi.rentcar.service;

import com.mehdi.rentcar.model.dto.CustomerData;

public interface CustomerService {

    void createCustomer(CustomerData customerData);

    void updateCustomer(CustomerData customerData);
}
