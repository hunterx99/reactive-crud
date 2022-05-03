package com.example.reactivecrud.service;

import com.example.reactivecrud.model.Customer;
import com.example.reactivecrud.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> findAll() {
        long startTime = System.currentTimeMillis();
        return customerRepository.findAll();
    }
}
