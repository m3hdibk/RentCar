package com.mehdi.rentcar.controller;

import com.mehdi.rentcar.model.dto.CarData;
import com.mehdi.rentcar.model.dto.request.CarRequest;
import com.mehdi.rentcar.model.dto.response.CarResponse;
import com.mehdi.rentcar.model.dto.response.PageResponse;
import com.mehdi.rentcar.service.CarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CarController {

    private final CarService carService;


    @GetMapping
    public ResponseEntity<?> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        PageResponse<CarResponse> response = carService.getAllCars(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCar(@RequestBody CarRequest carRequest) {
        carService.createCar(carRequest);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCar(@RequestBody CarRequest carRequest) {
        carService.updateCar(carRequest);
        return ResponseEntity.ok().build();
    }



}
