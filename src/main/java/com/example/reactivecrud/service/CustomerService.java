package com.example.reactivecrud.service;

import com.example.reactivecrud.model.Customer;
import com.example.reactivecrud.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }
}
