package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AirplaneCostEstimates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneCostEstimatesRepository extends JpaRepository<AirplaneCostEstimates,Long> {

  @Query(
      value =
          "SELECT * FROM AIRPLANE_COST_ESTIMATES WHERE AIRPLANE_ID = :airplaneId ORDER BY DATE_OF_ESTIMATE \n"
              + "DESC LIMIT 1;",
      nativeQuery = true)
  AirplaneCostEstimates getLastAirplaneCostEstimatesById(@Param("airplaneId") Long airplaneId);

}
