package com.basma.Demo1.ticket;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.feature.Feature;
import com.basma.Demo1.ticket.enums.Category;
import com.basma.Demo1.ticket.enums.Priority;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.ticket.enums.Type;
import com.basma.Demo1.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String title;
    private LocalDateTime creationDate;
//    private LocalDateTime createdAt;
    private LocalDateTime assignedAt;
    private LocalDateTime inProgressAt;
    private LocalDateTime solvedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime notApprovedAt;
    private LocalDateTime closedAt;
    private LocalDateTime cancelledAt;
//    @Enumerated(EnumType.STRING)
    private Status status;
//    @Enumerated(EnumType.STRING)
    private Type type;
    private Category categories;
//    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String solution;
    private String createdBy;
    private String assignedTo;
    private String firstname;
    private String lastname;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Application app;
}
