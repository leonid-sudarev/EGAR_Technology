package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 @AllArgsConstructor
@JsonTypeName("plane")
public class AirplaneDto implements Collateral {

  private Long id;
  private String brand;
  private String model;
  private String manufacturer;
  private Short year;
  private Integer fuelCapacity;
  private Integer seats;
  private BigDecimal value;
  private java.util.Date dateOfEstimate; // pattern = "yyyy-MM-dd"


}
