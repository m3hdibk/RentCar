package com.mehdi.rentcar.repository;

import com.mehdi.rentcar.model.Car;
import com.mehdi.rentcar.model.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {
}
