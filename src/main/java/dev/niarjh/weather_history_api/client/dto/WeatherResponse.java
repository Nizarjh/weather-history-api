package dev.niarjh.weather_history_api.client.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(

    @JsonProperty("weather_code")
    int weatherCode,
    
    @JsonProperty("temperature_2m")
    double temperature,

    @JsonProperty("apparent_temperature")
    double apparentTemperature,

    @JsonProperty("rain")
    double rainInMm,

    @JsonProperty("snowfall")
    double snowfall,

    @JsonProperty("wind_speed_10m")
    double windSpeed10m,

    @JsonProperty("wind_direction_10m")
    double windDirection10m,

    @JsonProperty("surface_pressure")
    double surfacePressure,

    @JsonProperty("pressure_msl")
    double pressureAtSeaLevel,

    @JsonProperty("relative_humidity_2m")
    int relativeHumidity2m,

    @JsonProperty("cloud_cover")
    int cloudCover,

    @JsonProperty("is_day")
    int isDay,

    @JsonProperty("time")
    LocalDateTime measuredAt

) {
}
