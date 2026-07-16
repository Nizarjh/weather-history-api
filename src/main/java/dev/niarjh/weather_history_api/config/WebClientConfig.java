package dev.niarjh.weather_history_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean WebClient weatherWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.open-meteo.com")
                .build();
    }

    @Bean WebClient geocodingWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://geocoding-api.open-meteo.com")
                .build();
    }
}
