package com.customercare.controller;

import com.customercare.dto.CustomerRequestDto;
import com.customercare.dto.CustomerResponseDto;
import com.customercare.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponseDto createCustomer(
            @Valid @RequestBody CustomerRequestDto request) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
