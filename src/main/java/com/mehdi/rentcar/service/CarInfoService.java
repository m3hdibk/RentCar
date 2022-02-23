package com.mehdi.rentcar.service;

import com.mehdi.rentcar.model.dto.request.CarInfoRequest;

public interface CarInfoService {

    long createCarInfo(CarInfoRequest carInfoRequest);
    void updateCarInfo(CarInfoRequest carInfoRequest);

}
