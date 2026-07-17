package dev.niarjh.weather_history_api.dto;

import java.time.LocalDateTime;

import dev.niarjh.weather_history_api.models.WeatherCode;

public record GetMeasurementResponseDto(
    double temperature,
    double apparentTemperature,
    WeatherCode weatherCode,
    double rainInMm,
    double snowfall,
    double windSpeed10m,
    double windDirection10m,
    double surfacePressure,
    double pressureAtSeaLevel,
    int relativeHumidity2m,
    int cloudCover,
    boolean isDay,
    LocalDateTime measuredAt

) {

}
