package com.airtravel.api.controller;

import com.airtravel.api.model.FlightLeg;
import com.airtravel.api.repository.FlightLegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@RequiredArgsConstructor
public class AircraftController {
    private final FlightLegRepository flightLegRepo;

    @GetMapping("/{aircraftId}/routes")
    public List<FlightLeg> getRoutes(@PathVariable Long aircraftId) {
        return flightLegRepo.findByAircraft_Id(aircraftId);
    }
}
