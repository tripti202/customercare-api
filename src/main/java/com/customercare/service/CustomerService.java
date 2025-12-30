package com.customercare.service;

import com.customercare.dto.CustomerRequestDto;
import com.customercare.dto.CustomerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto request);

    Page<CustomerResponseDto> getAllCustomers(Pageable pageable);

    CustomerResponseDto getCustomerById(Long id);

    void deleteCustomer(Long id);
}
