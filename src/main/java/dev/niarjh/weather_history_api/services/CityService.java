package dev.niarjh.weather_history_api.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.niarjh.weather_history_api.client.GeoCodingClient;
import dev.niarjh.weather_history_api.client.dto.GeocodingResponse;
import dev.niarjh.weather_history_api.dto.AddCityRequestDto;
import dev.niarjh.weather_history_api.dto.CitySearchFilter;
import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.mappers.CityMapper;
import dev.niarjh.weather_history_api.models.City;
import dev.niarjh.weather_history_api.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CityService {
    private final CityRepository repository;
    private final CityMapper cityMapper;
    private final GeoCodingClient geoCodingClient;

    public GetCityResponseDto getCityById(UUID id) {
        City cityEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + id));
        return cityMapper.toGetCityResponseDto(cityEntity);

    }

    public Page<GetCityResponseDto> getAllCities(CitySearchFilter filter) {
        Pageable pageable = getPageable(filter);
        return repository.findByFilter(
                filter.countryCode(),
                pageable).map(cityMapper::toGetCityResponseDto);
    }

    @Transactional
    public void addNewCity(AddCityRequestDto addCityRequestDto) {
        GeocodingResponse geocodingResponse = geoCodingClient.findCity(addCityRequestDto.cityName(),
                addCityRequestDto.countryCode());
        var results = geocodingResponse.results();

        if (results == null || results.isEmpty()) {
            throw new EntityNotFoundException("City not found");
        }
        City city = cityMapper.toCityEntity(results.getFirst());
        repository.save(city);
    }

    @Transactional
    public void deleteCityById(UUID id) {
        City city = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + id));
        repository.delete(city);
    }

    private static Pageable getPageable(CitySearchFilter filter) {
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
