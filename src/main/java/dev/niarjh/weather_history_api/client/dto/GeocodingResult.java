package dev.niarjh.weather_history_api.client.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeocodingResult(
        String name,
        @JsonProperty("country_code")
        String countryCode,
        BigDecimal latitude,
        BigDecimal longitude

) {
}