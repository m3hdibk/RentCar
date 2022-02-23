package com.mehdi.rentcar.repository;

import com.mehdi.rentcar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByVin(String vin);
}
