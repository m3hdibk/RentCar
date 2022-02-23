package com.mehdi.rentcar.service;

import com.mehdi.rentcar.model.dto.CarData;
import com.mehdi.rentcar.model.dto.request.CarRequest;
import com.mehdi.rentcar.model.dto.response.CarResponse;
import com.mehdi.rentcar.model.dto.response.PageResponse;


public interface CarService {


    void createCar(CarRequest carRequest);
    PageResponse<CarResponse> getAllCars(int page, int size);
    void updateCar(CarRequest carRequest);

}
