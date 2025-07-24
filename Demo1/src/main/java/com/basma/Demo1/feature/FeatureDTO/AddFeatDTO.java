package com.basma.Demo1.feature.FeatureDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter@Getter
public class AddFeatDTO {
    @NotBlank(message = "Name should not be blank")
    @Size(min = 5, message = "Name length invalid")
    private String name;
    @NotNull(message = "id should not be null")
    @Positive
    private Long appId;
    @NotBlank(message = "Description should not be blank")
    @Size(min = 10, message = "Description length invalid")
    private String description;
    private String devEmail;

}
