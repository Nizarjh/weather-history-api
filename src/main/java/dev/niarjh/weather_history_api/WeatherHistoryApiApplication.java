package dev.niarjh.weather_history_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WeatherHistoryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherHistoryApiApplication.class, args);
	}

}
