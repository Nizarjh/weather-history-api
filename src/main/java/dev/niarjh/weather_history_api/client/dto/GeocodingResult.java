package dev.niarjh.weather_history_api.client.dto;

import java.math.BigDecimal;

public record GeocodingResult(
        String name,
        String countryCode,
        BigDecimal latitude,
        BigDecimal longitude

) {
}