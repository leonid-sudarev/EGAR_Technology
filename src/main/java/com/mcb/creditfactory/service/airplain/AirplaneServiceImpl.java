package com.mcb.creditfactory.service.airplain;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AirplaneCostEstimates;
import com.mcb.creditfactory.repository.AirplaneCostEstimatesRepository;
import com.mcb.creditfactory.repository.AirplaneRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl implements AirplaneService {

  private AirplaneCostEstimatesRepository airplaneCostEstimatesRepository;
  private AirplaneRepository airplaneRepository;
  private ExternalApproveService approveService;

  @Override
  public boolean approve(AirplaneDto dto) {
    return approveService.approve(new AirplaneAdapter(dto)) == 0;
  }

  @Override
  public Airplane save(Airplane plane) {
    if (plane.getDateOfEstimate() != null) {
      plane.addAirplaneCostEstimates(
          generateAirplaneCostEstimates(plane, plane.getDateOfEstimate()));
    }
    return airplaneRepository.save(plane);
  }

  private AirplaneCostEstimates generateAirplaneCostEstimates(Airplane plane, java.util.Date date) {
    AirplaneCostEstimates airplaneCostEstimates = new AirplaneCostEstimates();
    airplaneCostEstimates.setValue(plane.getValue());
    airplaneCostEstimates.setDate(date);
    return airplaneCostEstimates;
  }

  @Override
  public Optional<Airplane> load(Long id) {
    return airplaneRepository.findById(id);
  }

  @Override
  public Airplane fromDTO(AirplaneDto planeDto) {
    return new Airplane(
        planeDto.getId(),
        planeDto.getBrand(),
        planeDto.getModel(),
        planeDto.getManufacturer(),
        planeDto.getYear(),
        planeDto.getFuelCapacity(),
        planeDto.getSeats(),
        planeDto.getValue(),
        planeDto.getDateOfEstimate());
  }

  @Override
  public AirplaneDto toDTO(Airplane planeDto) {
    AirplaneCostEstimates airplaneCostEstimates =
        airplaneCostEstimatesRepository.getLastAirplaneCostEstimatesById(planeDto.getId());

    return new AirplaneDto(
        planeDto.getId(),
        planeDto.getBrand(),
        planeDto.getModel(),
        planeDto.getManufacturer(),
        planeDto.getYear(),
        planeDto.getFuelCapacity(),
        planeDto.getSeats(),
        airplaneCostEstimates.getValue(),
        airplaneCostEstimates.getDate());
  }

  @Override
  public Long getId(Airplane plane) {
    return plane.getId();
  }

  @Autowired
  public void setApproveService(ExternalApproveService approveService) {
    this.approveService = approveService;
  }

  @Autowired
  public void setAirplaneCostEstimatesRepository(
      AirplaneCostEstimatesRepository airplaneCostEstimatesRepository) {
    this.airplaneCostEstimatesRepository = airplaneCostEstimatesRepository;
  }

  @Autowired
  public void setAirplaneRepository(AirplaneRepository airplaneRepository) {
    this.airplaneRepository = airplaneRepository;
  }
}
