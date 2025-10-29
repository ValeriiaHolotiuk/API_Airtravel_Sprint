package com.airtravel.api.controller;

import com.airtravel.api.model.City;
import com.airtravel.api.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

  private final CityRepository cityRepository;

  public CityController(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @GetMapping
  public List<City> all() {
    return cityRepository.findAll();
  }

  @PostMapping
  public City create(@RequestBody City city) {
    return cityRepository.save(city);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!cityRepository.existsById(id)) return ResponseEntity.notFound().build();
    cityRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
