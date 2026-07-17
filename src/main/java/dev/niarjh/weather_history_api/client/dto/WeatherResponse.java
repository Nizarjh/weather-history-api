package dev.niarjh.weather_history_api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(
    @JsonProperty("current")
    CurrentWeatherResponse current
) {}
