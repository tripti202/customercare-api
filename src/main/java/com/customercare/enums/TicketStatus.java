package com.customercare.enums;

/**
 * Represents lifecycle of a support ticket.
 * Designed to reflect real-world customer support flow.
 */
public enum TicketStatus {

    OPEN,          // Ticket created by customer
    IN_PROGRESS,   // Assigned to support agent
    RESOLVED,      // Solution provided, waiting for confirmation
    CLOSED;        // Final state (customer confirmed or auto-closed)

    /**
     * Validates legal status transitions.
     * Example: CLOSED → OPEN should not be allowed.
     */
    public boolean canTransitionTo(TicketStatus nextStatus) {

        if (this == OPEN && nextStatus == IN_PROGRESS) return true;
        if (this == IN_PROGRESS && (nextStatus == RESOLVED || nextStatus == OPEN)) return true;
        if (this == RESOLVED && nextStatus == CLOSED) return true;

        return false;
    }
}
