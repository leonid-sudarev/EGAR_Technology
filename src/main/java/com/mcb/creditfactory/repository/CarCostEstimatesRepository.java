package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.CarCostEstimates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCostEstimatesRepository extends JpaRepository<CarCostEstimates, Long> {

  @Query(
      value =
          "SELECT * FROM CAR_COST_ESTIMATES WHERE CAR_ID = :carId ORDER BY "
              + "DATE_OF_ESTIMATE \n"
              + "DESC LIMIT 1;",
      nativeQuery = true)
  CarCostEstimates getLastCarCostEstimatesById(@Param("carId") Long carId);
}
