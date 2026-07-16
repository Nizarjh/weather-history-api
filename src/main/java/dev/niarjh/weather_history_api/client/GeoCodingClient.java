package dev.niarjh.weather_history_api.client;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import dev.niarjh.weather_history_api.client.dto.GeocodingResponse;

@Component
public class GeoCodingClient {
    private final WebClient webClient;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(GeoCodingClient.class);

    public GeoCodingClient(@Qualifier("geocodingWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public GeocodingResponse findCity(String cityName, String countryCode) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                    .path("/v1/search")
                    .queryParam("name", cityName)
                    .queryParam("countryCode", countryCode)
                    .queryParam("count", 1)
                    .queryParam("format", "json")
                    .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GeocodingResponse.class)
                .doOnSuccess(city -> log.info("Found {}", city))
                .block();
    }
}
