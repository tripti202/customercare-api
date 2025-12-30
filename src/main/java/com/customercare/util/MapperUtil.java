package com.customercare.util;

import com.customercare.dto.CustomerResponseDto;
import com.customercare.dto.TicketResponseDto;
import com.customercare.entity.Customer;
import com.customercare.entity.Ticket;

public class MapperUtil {

    private MapperUtil() {
        // utility class
    }

    public static CustomerResponseDto mapToCustomerDto(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }

    public static TicketResponseDto mapToTicketDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setPriority(ticket.getPriority());
        dto.setStatus(ticket.getStatus());
        dto.setCreatedAt(ticket.getCreatedAt());
        return dto;
    }
}
