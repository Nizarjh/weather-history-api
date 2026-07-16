package dev.niarjh.weather_history_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetCityResponseDto(
                String name,
                String countryCode,
                BigDecimal latitude,
                BigDecimal longitude,
                LocalDateTime createdAt) {
}
