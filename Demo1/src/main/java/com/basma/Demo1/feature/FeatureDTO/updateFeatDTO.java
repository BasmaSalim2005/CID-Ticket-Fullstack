package com.basma.Demo1.feature.FeatureDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class updateFeatDTO {
    @Size(min = 5, message = "Name length invalid")
    private String name;

    @Size(min = 10, message = "Description length invalid")
    private String description;
}
