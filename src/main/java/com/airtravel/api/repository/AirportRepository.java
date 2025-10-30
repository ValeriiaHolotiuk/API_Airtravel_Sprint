package com.airtravel.api.repository;

import com.airtravel.api.model.Airport;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("""
      select a
      from Airport a
      where a.city.id = :cityId
    """)
    List<Airport> findByCityId(@Param("cityId") Long cityId);

    @Query("""
      select distinct ap
      from Airport ap
      where ap.id in (
        select fl.fromAirport.id
        from com.airtravel.api.model.FlightLeg fl
        where fl.aircraft in (
          select ac from com.airtravel.api.model.Passenger p join p.aircraft ac
          where p.id = :pid
        )
      )
      or ap.id in (
        select fl.toAirport.id
        from com.airtravel.api.model.FlightLeg fl
        where fl.aircraft in (
          select ac from com.airtravel.api.model.Passenger p join p.aircraft ac
          where p.id = :pid
        )
      )
    """)
    List<Airport> findAirportsUsedByPassenger(@Param("pid") Long passengerId);
}
