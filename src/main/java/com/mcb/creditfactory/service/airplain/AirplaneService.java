package com.mcb.creditfactory.service.airplain;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.model.Airplane;
import java.util.Optional;

public interface AirplaneService {
  boolean approve(AirplaneDto dto);
  Airplane save(Airplane plane);
  Optional<Airplane> load(Long id);
  Airplane fromDTO(AirplaneDto planeDto);
  AirplaneDto toDTO(Airplane planeDto);
  Long getId(Airplane plane);


}

