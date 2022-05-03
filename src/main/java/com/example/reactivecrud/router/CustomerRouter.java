package com.example.reactivecrud.router;

import com.example.reactivecrud.handler.CustomerHandler;
import com.example.reactivecrud.model.Customer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Customer API",
        version = "1.0",
        description = "Customer APIs v1.0"
))

public class CustomerRouter {
    private final CustomerHandler customerHandler;

    public CustomerRouter(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/customers",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = CustomerHandler.class,
                            beanMethod = "getAllCustomer",
                            operation = @Operation(
                                    operationId = "getAllCustomer",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "All Customer retrieved successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Customer.class
                                                    ))
                                            ),
                                            @ApiResponse(
                                                    responseCode = "400",
                                                    description = "No Customer Found"
                                            ),

                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/customer/{name}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = CustomerHandler.class,
                            beanMethod = "getCustomerByName",
                            operation = @Operation(
                                    operationId = "getCustomerByName",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "name", description = "customer name")
                                    },
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Customer retrieved successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Customer.class
                                                    ))
                                            ),
                                            @ApiResponse(
                                                    responseCode = "400",
                                                    description = "No Customer Found"
                                            ),

                                    }
                            )

                    )
            }
    )

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/customers", customerHandler::getAllCustomer)
                .GET("/customer/{name}", customerHandler::getCustomerByName)
                .build();
    }

}
