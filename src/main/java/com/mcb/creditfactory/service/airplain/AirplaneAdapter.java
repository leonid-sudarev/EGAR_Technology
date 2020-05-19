package com.mcb.creditfactory.service.airplain;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AirplaneAdapter implements CollateralObject {
  private final AirplaneDto airplane;

  public AirplaneAdapter(AirplaneDto airplane) {
    this.airplane = airplane;
  }

  @Override
  public BigDecimal getValue() {
    return airplane.getValue();
  }

  @Override
  public Short getYear() {
    return airplane.getYear();
  }

  @Override
  public LocalDate getDate() {
    if (airplane.getId() == null) { // new entry
      return LocalDate.now();
    }

    return new java.sql.Date(airplane.getDateOfEstimate().getTime()).toLocalDate();
  }

  @Override
  public String toString() {
    return "AirplaneAdapter{" + "airplane=" + airplane + '}';
  }

  @Override
  public CollateralType getType() {
    return CollateralType.AIRPLANE;
  }
}
