package dev.niarjh.weather_history_api.repositories;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.niarjh.weather_history_api.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {

        @Query("""
                        SELECT m
                        FROM Measurement m
                        WHERE m.city.cityId = :cityId
                        AND (cast(:from as LocalDateTime) IS NULL OR m.measuredAt >= :from)
                        AND (cast(:to as LocalDateTime) IS NULL OR m.measuredAt <= :to)
                        """)
        Page<Measurement> getAllMeasurementForCityByFilter(
                        @Param("cityId") UUID cityId,
                        @Param("from") LocalDateTime from,
                        @Param("to") LocalDateTime to,
                        Pageable pageable);

        Optional<Measurement> findFirstByCity_CityIdOrderByMeasuredAtDesc(UUID cityId);

}
