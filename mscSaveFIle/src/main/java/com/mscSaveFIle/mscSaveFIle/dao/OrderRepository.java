package com.mscSaveFIle.mscSaveFIle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findByCountryAndDateAndWeightLessThanEqual(String country, LocalDate date, Double weightLimit, Pageable pageable);
    Page<OrderEntity> findByCountryAndDate(String country, LocalDate date, Pageable pageable);
    Page<OrderEntity> findByCountryAndWeightLessThanEqual(String country, Double weightLimit, Pageable pageable);
    Page<OrderEntity> findByCountry(String country, Pageable pageable);
    Double calculateTotalWeightByCountry(String country);
    int countByCountry(String country);
}
