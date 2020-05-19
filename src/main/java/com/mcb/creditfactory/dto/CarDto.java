package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("car")
public class CarDto implements Collateral {

    private Long id;
    private String brand;
    private String model;
    private Double power;
    private Short year;
    private BigDecimal value;
    private Date dateOfEstimate;
}
