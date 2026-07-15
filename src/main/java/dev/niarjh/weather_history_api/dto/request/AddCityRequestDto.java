package dev.niarjh.weather_history_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddCityRequestDto(
                @NotBlank(message = "City name should not be blank") 
                @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters long.") 
                String cityName,

                @NotBlank(message = "Country code should not be blank")
                @Size(min = 2, max = 2) 
                String countryCode) {
}
