package com.example.reactivecrud.service;

import com.example.reactivecrud.model.Customer;
import com.example.reactivecrud.repo.CustomerRepository;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.function.BiFunction;

@Service
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerService {
    CustomerRepository customerRepository;
    DatabaseClient databaseClient;

    private final BiFunction<Row, RowMetadata, Customer> CUSTOMER_ROW_MAPPER = (row, rowMetadata) ->
            Customer
                    .builder()
                    .id(row.get("id", Integer.class))
                    .name(row.get("name", String.class))
                    .build();

    public CustomerService(CustomerRepository customerRepository, DatabaseClient databaseClient) {
        this.customerRepository = customerRepository;
        this.databaseClient = databaseClient;
    }

    public Flux<Customer> findByName(String name) {
        final String FIND_BY_NAME_SQL_QUERY = "SELECT * FROM customer WHERE name = :customerName";
        log.info("Going to query DB with name:" + name);
        return databaseClient
                .sql(FIND_BY_NAME_SQL_QUERY)
                .bind("customerName", name)
                .map(CUSTOMER_ROW_MAPPER)
                .all();
    }

    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }
}
