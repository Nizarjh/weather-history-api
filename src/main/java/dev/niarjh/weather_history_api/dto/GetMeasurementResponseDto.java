package dev.niarjh.weather_history_api.dto;

import java.time.LocalDateTime;

public record GetMeasurementResponseDto(
    double temperature,
    double apparentTemperature,
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
