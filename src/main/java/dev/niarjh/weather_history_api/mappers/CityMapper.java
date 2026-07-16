package dev.niarjh.weather_history_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import dev.niarjh.weather_history_api.client.dto.GeocodingResult;
import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.models.City;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    GetCityResponseDto toGetCityResponseDto(City city);

    @Mapping(target = "cityId", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    City toCityEntity(GeocodingResult geocoding);
}