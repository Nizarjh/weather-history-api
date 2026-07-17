package dev.niarjh.weather_history_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfig {

    @Bean
    RestClient weatherRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://api.open-meteo.com")
                .build();
    }

    @Bean
    RestClient geocodingRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://geocoding-api.open-meteo.com")
                .build();
    }
}
