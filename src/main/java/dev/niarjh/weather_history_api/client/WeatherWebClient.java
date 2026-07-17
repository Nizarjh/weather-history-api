package dev.niarjh.weather_history_api.client;

import java.math.BigDecimal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import dev.niarjh.weather_history_api.client.dto.WeatherResponse;

public class WeatherWebClient {
        private static final String CURRENT_FIELDS = String.join(",",
                        "temperature_2m",
                        "weather_code",
                        "rain",
                        "cloud_cover",
                        "wind_speed_10m",
                        "relative_humidity_2m",
                        "apparent_temperature",
                        "is_day",
                        "showers",
                        "surface_pressure",
                        "pressure_msl",
                        "snowfall",
                        "wind_direction_10m",
                        "wind_gusts_10m");
        private final WebClient webClient;
        public static final org.slf4j.Logger log = LoggerFactory.getLogger(WeatherWebClient.class);

        public WeatherWebClient(@Qualifier("weatherWebClient") WebClient webClient) {
                this.webClient = webClient;
        }

        public WeatherResponse findCity(BigDecimal latitude, BigDecimal longitude) {
                return webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                        .path("/v1/forecast")
                                        .queryParam("latitude", latitude)
                                        .queryParam("longitude", longitude)
                                        .queryParam("current", CURRENT_FIELDS)
                                        .queryParam("timezone", "auto")
                                        .build())
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono(WeatherResponse.class)
                                .doOnSuccess(city -> log.info("Found {}", city))
                                .block();
        }
}
