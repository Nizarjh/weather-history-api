package dev.niarjh.weather_history_api.controllers;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.niarjh.weather_history_api.dto.AddCityRequestDto;
import dev.niarjh.weather_history_api.dto.CitySearchFilter;
import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.services.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(CityController.class);

    @GetMapping
    public ResponseEntity<Page<GetCityResponseDto>> getCities(
            @RequestParam(required = false) String countryCode,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber) {
        var filter = new CitySearchFilter(countryCode, pageSize, pageNumber);
        log.info("Getting cities with filter={}", filter);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getAllCities(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCityResponseDto> getCityById(@PathVariable UUID id) {
        log.info("Called getCityById id={}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getCityById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCity(
            @RequestBody @Valid AddCityRequestDto addCityRequestDto) {
        cityService.addNewCity(addCityRequestDto);
        log.info("Creating city: cityName={}, countryCode={}", addCityRequestDto.cityName(),
                addCityRequestDto.countryCode());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable UUID id) {
        log.info("Called deleteCityById id={}", id);
        cityService.deleteCityById(id);
        return ResponseEntity.noContent().build();
    }

}
