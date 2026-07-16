package dev.niarjh.weather_history_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import dev.niarjh.weather_history_api.dto.GetCityResponseDto;
import dev.niarjh.weather_history_api.models.City;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    GetCityResponseDto toGetCityResponseDto(City city);
}