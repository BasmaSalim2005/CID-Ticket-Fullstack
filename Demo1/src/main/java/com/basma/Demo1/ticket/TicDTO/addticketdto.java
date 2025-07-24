package com.basma.Demo1.ticket.TicDTO;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.feature.Feature;
import com.basma.Demo1.ticket.enums.Category;
import com.basma.Demo1.ticket.enums.Priority;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.ticket.enums.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addticketdto {
    @NotBlank(message = "Description should not be blank")
//    @Size(min = 40, max = 1000, message = "Description length invalid")
    private String description;
    @NotBlank(message = "Tittle should not be blank")
//    @Size(min = 15, max = 35, message = "Tittle length invalid")
    private String title;
    @Email(message = "Email invalid")
    private String userEmail;
    @NotNull
    private Category categories;
    private Priority priority;
    private String location;
//    private Type type;

    private Status status;
    private String appname;
    private Long feature;
    private String createdBy;

    private String assignedTo;
    private String firstname;
    private String lastname;
}
