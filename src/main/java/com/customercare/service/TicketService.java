package com.customercare.service;

import com.customercare.dto.TicketRequestDto;
import com.customercare.dto.TicketResponseDto;
import com.customercare.enums.TicketStatus;

import java.util.List;

public interface TicketService {

    TicketResponseDto createTicket(Long customerId, TicketRequestDto request);

    List<TicketResponseDto> getTicketsByCustomer(Long customerId);

    TicketResponseDto getTicketById(Long ticketId);

    TicketResponseDto updateTicketStatus(Long ticketId, TicketStatus status);
}
