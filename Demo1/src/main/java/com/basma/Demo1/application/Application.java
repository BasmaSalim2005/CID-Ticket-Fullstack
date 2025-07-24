package com.basma.Demo1.application;

import com.basma.Demo1.application.enums.State;
import com.basma.Demo1.feature.Feature;
import com.basma.Demo1.ticket.Ticket;
import com.basma.Demo1.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Applications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name ;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private String gitLabLink;
    private String taigaLink;
    private String logoLink;
    @Enumerated(EnumType.STRING)
    private State state;
    //jira for proj manag

    @ManyToMany
    private List<User> Developers;
    //

    @OneToMany
    private List<Feature> appFeatures;

    @OneToMany
    private List<Ticket> tickets;
}
