package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplain.AirplaneService;

import com.mcb.creditfactory.service.car.CarService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollateralService {
  private CarService carService;
  private AirplaneService airplaneService;


  public Long saveCollateral(Collateral object) {
    if (object instanceof CarDto) {

      CarDto car = (CarDto) object;
      boolean approved = carService.approve(car);
      if (!approved) {
        return null;
      }

      return Optional.of(car)
          .map(carService::fromDTO)
          .map(carService::save)
          .map(carService::getId)
          .orElse(null);

    } else if (object instanceof AirplaneDto) {

      AirplaneDto plain = (AirplaneDto) object;
      boolean approved = airplaneService.approve(plain);
      if (!approved) {
        return null;
      }
      return Optional.of(plain)
          .map(airplaneService::fromDTO)
          .map(airplaneService::save)
          .map(airplaneService::getId)
          .orElse(null);

    } else {
      throw new IllegalArgumentException("Illegal instance");
    }
  }

  public Collateral getInfo(Collateral object) {
    if (object instanceof CarDto) {

      return Optional.of((CarDto) object)
          .map(carService::fromDTO)
          .map(carService::getId)
          .flatMap(carService::load)
          .map(carService::toDTO)
          .orElse(null);

    } else if (object instanceof AirplaneDto) {

      return Optional.of((AirplaneDto) object)
          .map(airplaneService::fromDTO)
          .map(airplaneService::getId)
          .flatMap(airplaneService::load)
          .map(airplaneService::toDTO)
          .orElse(null);

    } else {
      throw new IllegalArgumentException("Illegal instance");
    }
  }

  @Autowired
  public void setAirplaneService(AirplaneService airplaneService) {
    this.airplaneService = airplaneService;
  }

  @Autowired
  public void setCarService(CarService carService) {
    this.carService = carService;
  }


}
