package com.basma.Demo1.user;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.ticket.Ticket;
import com.basma.Demo1.user.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.util.List;

@Entity
@Table(name="Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Role role;

    @OneToMany
    private List<Ticket> ticket ;

    @ManyToMany
    private List<Application> apps;
}
