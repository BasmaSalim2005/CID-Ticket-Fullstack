package com.basma.Demo1.feedback.FeedbackDTO;

import com.basma.Demo1.feedback.enums.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class addFeedbackDTO {
//    @NotNull(message = "Name should not be Null")
//    private Type type;
    @NotBlank(message = "Comment should not be blank")
    @Size(min = 15, message = "Content length invalid")
    private String comment;
    private Long applicationId;
    private Integer usability;
    private Integer satisfaction;
    private Integer performance;
    private Integer design;
    private Integer reliability;
    private Integer overall;

    @Email
    @NotNull
    private String userEmail;
    private String appName;
}
