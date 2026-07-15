package dev.niarjh.weather_history_api.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.niarjh.weather_history_api.dto.AddCityRequestDto;
import dev.niarjh.weather_history_api.dto.CitySearchFilter;
import dev.niarjh.weather_history_api.models.City;
import dev.niarjh.weather_history_api.services.CityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(CityController.class);

    @GetMapping
    public ResponseEntity<List<City>> getAllCities(
            @RequestParam(required = false) String countryCode,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber) {
        var filter = new CitySearchFilter(countryCode, pageSize, pageNumber);
        log.info("Getting cities with filter={}", filter);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getAllCities(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable UUID id) {
        log.info("Called getCityById id={}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getCityById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addNewCity(
            @RequestBody @Valid AddCityRequestDto addCityRequestDto) {
        cityService.addNewCity(addCityRequestDto);
        log.info("Creating city: cityName={}, countryCode={}", addCityRequestDto.cityName(),
                addCityRequestDto.countryCode());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable UUID id) {
        log.info("Called deleteCityById id={}", id);
        cityService.deleteCityById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
