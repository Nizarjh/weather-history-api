package dev.niarjh.weather_history_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MeasurementSearchFilter(
        UUID cityId,
        LocalDateTime from,
        LocalDateTime to,
        Integer pageSize,
        Integer pageNumber) {

}
