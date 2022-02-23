package com.mehdi.rentcar.controller;

import com.mehdi.rentcar.model.dto.CustomerData;
import com.mehdi.rentcar.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerData customerData) {
        customerService.createCustomer(customerData);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerData customerData) {
        customerService.updateCustomer(customerData);
        return ResponseEntity.ok().build();
    }



}
