package com.basma.Demo1.ticket.TicDTO;

import com.basma.Demo1.ticket.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketStatusDetailsDTO {
    private Long ticketId;
    private Status status; // ✅ Add this!
    private LocalDateTime createdAt;
    private LocalDateTime assignedAt;
    private LocalDateTime inProgressAt;
    private LocalDateTime solvedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime notApprovedAt;
    private LocalDateTime closedAt;
    private LocalDateTime cancelledAt;
}
