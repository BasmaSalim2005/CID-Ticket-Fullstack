package com.basma.Demo1.ticket.TicDTO;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class devOrTechDTO {
    private String fullname;
    private String email;
    private Long ticketCount;
    private Long inprogress;

    public devOrTechDTO(String fullname, String email, Long ticketCount, Long inprogress) {
        this.fullname = fullname;
        this.email = email;
        this.ticketCount = ticketCount;
        this.inprogress = inprogress;
    }

    // Getters and setters (or use Lombok @Data if you prefer)
}

