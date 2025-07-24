package com.basma.Demo1.feedback.FeedbackDTO;

import com.basma.Demo1.feedback.enums.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class updateFeedbackDTO {

    @NotBlank(message = "Comment should not be blank")
    @Size(min = 15, message = "Comment length invalid")
    private String comment;
    private Integer usability;
    private Integer Satisfaction;
    private Integer performance;
    private Integer design;
    private Integer reliability;
    private Integer overall;
}
