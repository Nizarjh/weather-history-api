package dev.niarjh.weather_history_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID measurementId;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private double apparentTemperature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeatherCode weatherCode;

    @Column(nullable = false)
    private double rainInMm;

    @Column(nullable = false)
    private double snowfall;

    @Column(name = "wind_speed_10m", nullable = false)
    private double windSpeed10m;

    @Column(name = "wind_direction_10m", nullable = false)
    private double windDirection10m;

    @Column(nullable = false)
    private double surfacePressure;

    @Column(nullable = false)
    private double pressureAtSeaLevel;

    @Column(name = "relative_humidity_2m", nullable = false)
    private int relativeHumidity2m;

    @Column(nullable = false)
    private int cloudCover;

    @Column(nullable = false)
    private boolean isDay;

    @Column(nullable = false, updatable = false)
    LocalDateTime measuredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}