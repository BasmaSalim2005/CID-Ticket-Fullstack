package com.basma.Demo1.application.AppDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class addAppDTO {
    @NotBlank(message = "Name should not be blank")
    @Size(min = 3, max = 10, message = "Name length invalid")
    private String name ;
    @NotBlank(message = "Description should not be blank")
    @Size(min = 10, message = "Description length invalid")
    private String description;
    @NotBlank(message = "Github link should not be blank")
    @Pattern(
            regexp = "^https://github\\.com/.*$",
            message = "GitHub link must start with https://github.com/"
    )
    private String gitLabLink;
    @NotBlank(message = "Taiga link should not be blank")
    @Pattern(
            regexp = "^https://taiga\\.com/.*$",
            message = "GitHub link must start with https://taiga.com/"
    )
    private String devEmail;
    private String taigaLink;
    private String logoLink;
}
