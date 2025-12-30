package com.customercare.controller;

import com.customercare.dto.TicketRequestDto;
import com.customercare.dto.TicketResponseDto;
import com.customercare.enums.TicketStatus;
import com.customercare.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<TicketResponseDto> createTicket(
            @PathVariable Long customerId,
            @RequestBody TicketRequestDto request) {

        return new ResponseEntity<>(
                ticketService.createTicket(customerId, request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TicketResponseDto>> getTicketsByCustomer(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(ticketService.getTicketsByCustomer(customerId));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto> getTicketById(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @PatchMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponseDto> updateTicketStatus(
            @PathVariable Long ticketId,
            @RequestParam TicketStatus status) {

        return ResponseEntity.ok(
                ticketService.updateTicketStatus(ticketId, status)
        );
    }
}
