package com.airtravel.api.controller;

import com.airtravel.api.model.*;
import com.airtravel.api.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/legs")
public class FlightLegController {

    private final FlightLegRepository legRepo;
    private final AircraftRepository aircraftRepo;
    private final AirportRepository airportRepo;

    public FlightLegController(FlightLegRepository legRepo, AircraftRepository aircraftRepo, AirportRepository airportRepo) {
        this.legRepo = legRepo;
        this.aircraftRepo = aircraftRepo;
        this.airportRepo = airportRepo;
    }


    @PostMapping
    public ResponseEntity<FlightLeg> create(@RequestParam Long aircraftId,
                                            @RequestParam Long fromAirportId,
                                            @RequestParam Long toAirportId) {
        Optional<Aircraft> a = aircraftRepo.findById(aircraftId);
        Optional<Airport> from = airportRepo.findById(fromAirportId);
        Optional<Airport> to = airportRepo.findById(toAirportId);
        if (a.isEmpty() || from.isEmpty() || to.isEmpty()) return ResponseEntity.badRequest().build();

        FlightLeg leg = new FlightLeg();
        leg.setAircraft(a.get());
        leg.setFromAirport(from.get());
        leg.setToAirport(to.get());
        return ResponseEntity.ok(legRepo.save(leg));
    }
}
