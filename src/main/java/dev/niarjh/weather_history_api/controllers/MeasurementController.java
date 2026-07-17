package dev.niarjh.weather_history_api.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.dto.GetMeasurementResponseDto;
import dev.niarjh.weather_history_api.dto.MeasurementSearchFilter;
import dev.niarjh.weather_history_api.services.MeasurementService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cities/{cityId}/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(MeasurementController.class);

    @GetMapping
    public ResponseEntity<Page<GetMeasurementResponseDto>> getAllMeasurementForCity(
            @PathVariable UUID cityId,
            @RequestParam(required = false) LocalDateTime from,
            @RequestParam(required = false) LocalDateTime to,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber) {
        var filter = new MeasurementSearchFilter(cityId, from, to, pageSize, pageNumber);
        log.info("Getting measurements with filter={}", filter);
        return ResponseEntity.status(HttpStatus.OK)
                .body(measurementService.getAllMeasurementForCity(filter));
    }

    @GetMapping("/latest")
    public ResponseEntity<GetMeasurementResponseDto> getLatestMeasurement(@PathVariable UUID cityId) {
        log.info("Called getLatestMeasurement id={}", cityId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(measurementService.getLatestMeasurement(cityId));
    }

}
