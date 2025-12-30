package com.customercare.service.impl;

import com.customercare.dto.TicketRequestDto;
import com.customercare.dto.TicketResponseDto;
import com.customercare.entity.Customer;
import com.customercare.entity.Ticket;
import com.customercare.enums.TicketStatus;
import com.customercare.exception.ResourceNotFoundException;
import com.customercare.repository.CustomerRepository;
import com.customercare.repository.TicketRepository;
import com.customercare.service.TicketService;
import com.customercare.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public TicketResponseDto createTicket(Long customerId, TicketRequestDto request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id " + customerId));

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCustomer(customer);

        Ticket saved = ticketRepository.save(ticket);
        return MapperUtil.mapToTicketDto(saved);
    }

    @Override
    public List<TicketResponseDto> getTicketsByCustomer(Long customerId) {
        return ticketRepository.findByCustomerId(customerId)
                .stream()
                .map(MapperUtil::mapToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDto getTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found with id " + ticketId));

        return MapperUtil.mapToTicketDto(ticket);
    }

    @Override
    public TicketResponseDto updateTicketStatus(Long ticketId, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found with id " + ticketId));

        ticket.setStatus(status);
        return MapperUtil.mapToTicketDto(ticketRepository.save(ticket));
    }
}
