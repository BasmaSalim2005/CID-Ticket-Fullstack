package com.basma.Demo1.feedback.FeedbackDTO;

import com.basma.Demo1.feedback.enums.Type;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FeedbackResponseDTO {
        private String comment;
        private Integer usability;
        private Integer satisfaction;
        private Integer performance;
        private Integer design;
        private Integer reliability;
        private Integer overall;
        private String userEmail;
        private String appName;
        private LocalDateTime creationDate;
    }


