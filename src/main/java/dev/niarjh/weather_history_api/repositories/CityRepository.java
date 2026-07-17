package dev.niarjh.weather_history_api.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.niarjh.weather_history_api.models.City;

public interface CityRepository extends JpaRepository<City, UUID> {

    @Query("""
            SELECT c
            FROM City c
            WHERE (:countryCode IS NULL OR c.countryCode = :countryCode)
            """)
    Page<City> findByFilter(
            @Param("countryCode") String countryCode,
            Pageable pageable);
}