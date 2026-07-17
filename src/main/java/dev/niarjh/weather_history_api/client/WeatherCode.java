package dev.niarjh.weather_history_api.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeatherCode {
    CLEAR_SKY(0),

    MAINLY_CLEAR(1),
    PARTLY_CLOUDY(2),
    OVERCAST(3),

    FOG(45),
    RIME_FOG(48),

    LIGHT_DRIZZLE(51),
    MODERATE_DRIZZLE(53),
    DENSE_DRIZZLE(55),

    LIGHT_FREEZING_DRIZZLE(56),
    DENSE_FREEZING_DRIZZLE(57),

    SLIGHT_RAIN(61),
    MODERATE_RAIN(63),
    HEAVY_RAIN(65),

    LIGHT_FREEZING_RAIN(66),
    HEAVY_FREEZING_RAIN(67),

    SLIGHT_SNOW(71),
    MODERATE_SNOW(73),
    HEAVY_SNOW(75),

    SNOW_GRAINS(77),

    SLIGHT_RAIN_SHOWERS(80),
    MODERATE_RAIN_SHOWERS(81),
    VIOLENT_RAIN_SHOWERS(82),

    SLIGHT_SNOW_SHOWERS(85),
    HEAVY_SNOW_SHOWERS(86),

    THUNDERSTORM(95),
    THUNDERSTORM_WITH_HAIL(96),
    SEVERE_THUNDERSTORM_WITH_HAIL(99);

    private final int code;

    public static WeatherCode fromCode(int code) {
        for (WeatherCode value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown weather code: " + code);
    }
}