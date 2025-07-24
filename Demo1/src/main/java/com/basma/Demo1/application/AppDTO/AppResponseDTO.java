package com.basma.Demo1.application.AppDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AppResponseDTO {
    private String name ;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private String gitLabLink;
    private String taigaLink;

}
