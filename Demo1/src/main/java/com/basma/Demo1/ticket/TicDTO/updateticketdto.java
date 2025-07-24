package com.basma.Demo1.ticket.TicDTO;

import com.basma.Demo1.ticket.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class updateticketdto {
    @NonNull
    @NotBlank(message = "Description should not be blank")
    private String description;
    @NonNull
    @NotBlank(message = "Tittle should not be blank")
    private String title;
    @NonNull
    @NotBlank(message = "Status should not be blank")
    private Status status;
}
