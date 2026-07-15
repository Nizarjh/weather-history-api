package dev.niarjh.weather_history_api.dto;

public record CitySearchFilter(
        String countryCode,
        Integer pageSize,
        Integer pageNumber) {
}
