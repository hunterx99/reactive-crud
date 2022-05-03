package com.example.reactivecrud.router;

import com.example.reactivecrud.handler.CustomerHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Customer API",
        version = "1.0",
        description = "List APIs v1.0"
))

public class CustomerRouter {
    private final CustomerHandler customerHandler;

    public CustomerRouter(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/customers", customerHandler::getAllCustomer)
                .build();
    }

}
