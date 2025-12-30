package com.customercare.service.impl;

import com.customercare.dto.CustomerRequestDto;
import com.customercare.dto.CustomerResponseDto;
import com.customercare.entity.Customer;
import com.customercare.exception.ResourceNotFoundException;
import com.customercare.repository.CustomerRepository;
import com.customercare.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Customer with email already exists");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer saved = customerRepository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToResponse(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    private CustomerResponseDto mapToResponse(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }
}
