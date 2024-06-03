package com.example.severstaltest.repository;

import com.example.severstaltest.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findDeliveriesByDate(LocalDate localDate);
    @Query(value = "from Delivery t where t.date BETWEEN :startDate AND :endDate")
    Optional<List<Delivery>> getAllDeliveriesBetweenDates(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
}
