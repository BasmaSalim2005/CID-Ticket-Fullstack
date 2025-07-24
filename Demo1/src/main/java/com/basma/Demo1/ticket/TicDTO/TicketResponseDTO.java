package com.basma.Demo1.ticket.TicDTO;

import com.basma.Demo1.ticket.enums.Category;
import com.basma.Demo1.ticket.enums.Priority;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.ticket.enums.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TicketResponseDTO {
    private long id ;
    private String description;
    private String title;
    private String userEmail;
//    private String assignedToEmail;
    private String assignedTo;
    private String firstname;
    private String lastname;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime assignedAt;
    private LocalDateTime inProgressAt;
    private LocalDateTime solvedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime closedAt;
    private LocalDateTime cancelledAt;
    private Status status;
    private Priority priority;
    private Category categories;
    private String location;
    private Type type;
}
