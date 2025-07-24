package com.basma.Demo1.application.AppDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AppKPIsDTO {
    private Integer total;
    private Integer active;
    private Integer disactive;
    private Integer inmaintenance;
}
