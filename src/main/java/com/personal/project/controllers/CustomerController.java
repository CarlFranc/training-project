package com.personal.project.controllers;

import com.personal.project.entity.CustomerEntity;
import com.personal.project.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers/{customerID}" , produces = "application/json")
    ResponseEntity<CustomerEntity> listAllCustomers(@PathVariable UUID customerID) {
        log.info("Customer ID : {}", customerID);
        Optional<CustomerEntity> customer = customerService.getCustomer(customerID);

        if (customer.isEmpty()) {
            log.error("Customer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customer.get());
    }

    @PostMapping(value = "/customers")
    ResponseEntity<CustomerEntity> createCustomer(@Validated @RequestBody CustomerEntity customerEntity) {
        log.info("Customer : {}", customerEntity);
        CustomerEntity customer = customerService.saveCustomer(customerEntity);

        return ResponseEntity.ok(customer);
    }

}
