package com.airtravel.api.controller;

import com.airtravel.api.model.Airport;
import com.airtravel.api.model.City;
import com.airtravel.api.repository.AirportRepository;
import com.airtravel.api.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityRepository cityRepo;
    private final AirportRepository airportRepo;

    public CityController(CityRepository cityRepo, AirportRepository airportRepo) {
        this.cityRepo = cityRepo;
        this.airportRepo = airportRepo;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {
        Optional<City> city = cityRepo.findById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City saved = cityRepo.save(city);
        return ResponseEntity.created(URI.create("/api/cities/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City updated) {
        return cityRepo.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setState(updated.getState());
                    existing.setPopulation(updated.getPopulation());
                    return ResponseEntity.ok(cityRepo.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        if (!cityRepo.existsById(id)) return ResponseEntity.notFound().build();
        cityRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsInCity(@PathVariable Long id) {
        if (!cityRepo.existsById(id)) return ResponseEntity.notFound().build();
        List<Airport> airports = airportRepo.findByCityId(id);
        return ResponseEntity.ok(airports);
    }
}
