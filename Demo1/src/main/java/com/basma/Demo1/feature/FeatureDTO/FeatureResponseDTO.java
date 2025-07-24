package com.basma.Demo1.feature.FeatureDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter@Getter
public class FeatureResponseDTO {
    private long id;
    private String name;
    private String appName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAT;
    private String devFname;
    private  String devLname;
}
