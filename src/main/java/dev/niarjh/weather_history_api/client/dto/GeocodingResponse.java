package dev.niarjh.weather_history_api.client.dto;

import java.util.List;

public record GeocodingResponse(
        List<GeocodingResult> results) {

}
