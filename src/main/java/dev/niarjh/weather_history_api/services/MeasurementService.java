package dev.niarjh.weather_history_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.niarjh.weather_history_api.client.WeatherClient;
import dev.niarjh.weather_history_api.client.dto.WeatherResponse;
import dev.niarjh.weather_history_api.dto.GetMeasurementResponseDto;
import dev.niarjh.weather_history_api.dto.MeasurementSearchFilter;
import dev.niarjh.weather_history_api.mappers.MeasurementMapper;
import dev.niarjh.weather_history_api.models.City;
import dev.niarjh.weather_history_api.models.Measurement;
import dev.niarjh.weather_history_api.repositories.CityRepository;
import dev.niarjh.weather_history_api.repositories.MeasurementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final CityRepository cityRepository;
    private final MeasurementMapper measurementMapper;
    private final WeatherClient weatherWebClient;

    public Page<GetMeasurementResponseDto> getAllMeasurementForCity(MeasurementSearchFilter filter) {
        Pageable pageable = getPageable(filter);
        return measurementRepository.getAllMeasurementForCityByFilter(
                filter.cityId(),
                filter.from(),
                filter.to(),
                pageable).map(measurementMapper::toGetMeasurementResponseDto);

    }

    public GetMeasurementResponseDto getLatestMeasurement(UUID cityId) {
        Measurement measurement = measurementRepository.findFirstByCity_CityIdOrderByMeasuredAtDesc(cityId)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + cityId));
        return measurementMapper.toGetMeasurementResponseDto(measurement);
    }

    @Transactional
    @Scheduled(fixedRate = 15 * 60 * 1000)
    public void autoupdateMeasurements() {

        List<City> cities = cityRepository.findAll();

        for (City city : cities) {

            WeatherResponse weatherResponse = weatherWebClient.getCurrentWeather(
                    city.getLatitude(),
                    city.getLongitude());

            Measurement measurement = measurementMapper.toMeasurement(weatherResponse.current());
            measurement.setCity(city);
            measurementRepository.save(measurement);
        }
    }

    private static Pageable getPageable(MeasurementSearchFilter filter) {
        int pageSize = filter.pageSize() != null
                ? filter.pageSize()
                : 15;
        int pageNumber = filter.pageNumber() != null
                ? filter.pageNumber()
                : 0;

        return Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);
    }
}
