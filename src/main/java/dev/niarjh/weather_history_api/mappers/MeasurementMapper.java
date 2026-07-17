package dev.niarjh.weather_history_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import dev.niarjh.weather_history_api.client.dto.CurrentWeatherResponse;
import dev.niarjh.weather_history_api.dto.GetMeasurementResponseDto;
import dev.niarjh.weather_history_api.models.Measurement;
import dev.niarjh.weather_history_api.models.WeatherCode;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasurementMapper {

    @Mapping(target = "isDay", source = "day")
    GetMeasurementResponseDto toGetMeasurementResponseDto(Measurement measurement);

    @Mapping(target = "measurementId", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "weatherCode", source = "weatherCode")
    @Mapping(target = "day", source = "isDay")
    Measurement toMeasurement(CurrentWeatherResponse c);

    default boolean map(int isDay) {
        return isDay == 1;
    }

    default WeatherCode mapWeatherCode(int weatherCode) {
        return WeatherCode.fromCode(weatherCode);
    }
}
