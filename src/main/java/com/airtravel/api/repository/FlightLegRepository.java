package com.airtravel.api.repository;

import com.airtravel.api.model.FlightLeg;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightLegRepository extends JpaRepository<FlightLeg, Long> {

    List<FlightLeg> findByAircraft_Id(Long aircraftId);
}
