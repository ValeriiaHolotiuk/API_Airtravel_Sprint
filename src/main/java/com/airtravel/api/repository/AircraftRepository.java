package com.airtravel.api.repository;

import com.airtravel.api.model.Aircraft;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    @Query("""
       select distinct a
       from com.airtravel.api.model.Passenger p
       join p.aircraft a
       where p.id = :pid
    """)
    List<Aircraft> findAircraftByPassengerId(@Param("pid") Long passengerId);
}
