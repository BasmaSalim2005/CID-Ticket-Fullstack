package com.basma.Demo1.ticket.TicDTO;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.feature.Feature;
import com.basma.Demo1.ticket.enums.Category;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.ticket.enums.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class assignTicDTO {
    private long id;

    private Application app;
    private Feature feature;

}
