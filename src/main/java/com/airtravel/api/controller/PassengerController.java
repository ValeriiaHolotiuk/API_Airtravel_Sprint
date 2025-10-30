package com.airtravel.api.controller;

import com.airtravel.api.model.Aircraft;
import com.airtravel.api.model.Airport;
import com.airtravel.api.repository.AircraftRepository;
import com.airtravel.api.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final AircraftRepository aircraftRepo;
    private final AirportRepository  airportRepo;

    @GetMapping("/{pid}/aircraft")
    public List<Aircraft> getAircraftFlownByPassenger(@PathVariable Long pid) {
        return aircraftRepo.findAircraftByPassengerId(pid);
    }

    @GetMapping("/{pid}/airports")
    public List<Airport> getAirportsUsedByPassenger(@PathVariable Long pid) {
        return airportRepo.findAirportsUsedByPassenger(pid);
    }
}
