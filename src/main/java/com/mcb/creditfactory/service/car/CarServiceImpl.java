package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CarCostEstimates;
import com.mcb.creditfactory.repository.CarCostEstimatesRepository;
import com.mcb.creditfactory.repository.CarRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  private CarCostEstimatesRepository carCostEstimatesRepository;
  private CarRepository carRepository;
  private ExternalApproveService approveService;

  @Override
  public boolean approve(CarDto dto) {
    return approveService.approve(new CarAdapter(dto)) == 0;
  }

  @Override
  public Car save(Car car) {
    if (car.getDateOfEstimate() != null) {
      car.addCarCostEstimates(generateCarCostEstimates(car, car.getDateOfEstimate()));
    }

    return carRepository.save(car);
  }

  private CarCostEstimates generateCarCostEstimates(Car car, Date date) {
    CarCostEstimates carCostEstimates = new CarCostEstimates();
    carCostEstimates.setValue(car.getValue());
    carCostEstimates.setDate(date);
    return carCostEstimates;
  }

  @Override
  public Optional<Car> load(Long id) {
    return carRepository.findById(id);
  }

  @Override
  public Car fromDTO(CarDto carDto) {
    return new Car(
        carDto.getId(),
        carDto.getBrand(),
        carDto.getModel(),
        carDto.getPower(),
        carDto.getYear(),
        carDto.getValue(),
        carDto.getDateOfEstimate());
  }

  @Override
  public CarDto toDTO(Car car) {
    CarCostEstimates carCostEstimates =
        carCostEstimatesRepository.getLastCarCostEstimatesById(car.getId());
    return new CarDto(
        car.getId(),
        car.getBrand(),
        car.getModel(),
        car.getPower(),
        car.getYear(),
        carCostEstimates.getValue(),
        carCostEstimates.getDate());
  }

  @Override
  public Long getId(Car car) {
    return car.getId();
  }

  @Autowired
  public void setApproveService(ExternalApproveService approveService) {
    this.approveService = approveService;
  }

  @Autowired
  public void setCarCostEstimatesRepository(CarCostEstimatesRepository carCostEstimatesRepository) {
    this.carCostEstimatesRepository = carCostEstimatesRepository;
  }

  @Autowired
  public void setCarRepository(CarRepository carRepository) {
    this.carRepository = carRepository;
  }
}
