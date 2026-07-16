package dev.niarjh.weather_history_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.models.City;

@Mapper
public interface CityMapper {

    @Mapping(target = "cityId", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    GetCityResponseDto carToGetCityResponseDto(City city);
}