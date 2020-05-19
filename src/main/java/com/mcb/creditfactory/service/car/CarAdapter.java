package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CarAdapter implements CollateralObject {
  private final CarDto car;

  public CarAdapter(CarDto car) {
    this.car = car;
  }

  @Override
  public BigDecimal getValue() {
    return car.getValue();
  }

  @Override
  public Short getYear() {
    return car.getYear();
  }

  @Override
  public LocalDate getDate() {
    if (car.getId() == null) { // new entry
      return LocalDate.now();
    }
    return new java.sql.Date(car.getDateOfEstimate().getTime()).toLocalDate();
  }

  @Override
  public CollateralType getType() {
    return CollateralType.CAR;
  }
}
