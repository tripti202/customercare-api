package com.customercare.enums;

/**
 * Indicates urgency of the ticket.
 * Used for SLA handling and prioritization.
 */
public enum TicketPriority {

    LOW(72),
    MEDIUM(48),
    HIGH(24);

    // SLA in hours
    private final int slaHours;

    TicketPriority(int slaHours) {
        this.slaHours = slaHours;
    }

    public int getSlaHours() {
        return slaHours;
    }
}
