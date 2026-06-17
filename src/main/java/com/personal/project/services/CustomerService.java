package com.personal.project.services;

import com.personal.project.entity.CustomerEntity;
import com.personal.project.repositories.CustomerRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Optional<CustomerEntity> getCustomer(@NonNull UUID customerID) {

        return customerRepository.findById(customerID);
    }

    public CustomerEntity saveCustomer(@NonNull CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

}
