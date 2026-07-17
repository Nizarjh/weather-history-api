package dev.niarjh.weather_history_api.client;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import dev.niarjh.weather_history_api.client.dto.GeocodingResponse;

@Component
public class GeoCodingClient {
    private final RestClient restClient;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(GeoCodingClient.class);

    public GeoCodingClient(@Qualifier("geocodingRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public GeocodingResponse findCity(String cityName, String countryCode) {
        return restClient.get().uri(uriBuilder -> uriBuilder
                .path("/v1/search")
                .queryParam("name", cityName)
                .queryParam("countryCode", countryCode)
                .queryParam("count", 1)
                .queryParam("format", "json")
                .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(GeocodingResponse.class);
    }
}
