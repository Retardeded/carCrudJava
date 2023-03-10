package com.example.carCrud.repository;

import com.example.carCrud.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandName(String brand);
}