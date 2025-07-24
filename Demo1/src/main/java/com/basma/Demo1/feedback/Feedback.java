package com.basma.Demo1.feedback;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.feedback.enums.Type;
import com.basma.Demo1.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Feedback")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;
//    private Type type;
    private Integer usability;
    private Integer Satisfaction;
    private Integer performance;
    private Integer design;
    private Integer reliability;
    private Integer overall;
    private String comment;

    private LocalDateTime creationDate;

    @ManyToOne
    private Application app;

    @ManyToOne
    private User user;
}
