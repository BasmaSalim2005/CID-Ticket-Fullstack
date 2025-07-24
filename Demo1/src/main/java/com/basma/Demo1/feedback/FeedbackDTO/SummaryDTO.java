package com.basma.Demo1.feedback.FeedbackDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter@Getter
public class SummaryDTO {

    private String userLName;
    private String userFName;
    private String userEmail;
    private Integer usability;
    private Integer satisfaction;
    private Integer performance;
    private Integer design;
    private Integer reliability;
    private Integer overall;
    private String comment;
    private LocalDateTime creationDate;

    public SummaryDTO(String lastname, String firstname, String email,
                              Integer usability, Integer satisfaction, Integer performance,
                      Integer design, Integer reliability, Integer overall, String comment) {
        this.userLName = lastname;
        this.userFName = firstname;
        this.userEmail = email;
        this.usability = usability;
        this.satisfaction = satisfaction;
        this.performance = performance;
        this.design = design;
        this.reliability = reliability;
        this.overall = overall;
        this.comment = comment;
        LocalDateTime now = LocalDateTime.now();
        this.creationDate =now;
    }

}
