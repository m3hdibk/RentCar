package com.mehdi.rentcar.mapper;

import com.mehdi.rentcar.model.*;
import com.mehdi.rentcar.model.dto.*;
import com.mehdi.rentcar.model.dto.response.CarInfoResponse;
import com.mehdi.rentcar.model.dto.response.CarResponse;


public interface Mapper {


    CarData carEntityToData(Car car);
    Car carDataToEntity(CarData carData);
    CarResponse carEntityToResponse(Car car);

    SchemeData schemeEntityToData(Scheme scheme);
    Scheme schemeDataToEntity(SchemeData schemeData);
    IdValueObject schemeEntityToIdValue(Scheme scheme);

    CarInfoData carInfoEntityToData(CarInfo carInfo);
    CarInfo carInfoDataToEntity(CarInfoData carInfoData);
    CarInfoResponse carInfoEntityToResponse(CarInfo carInfo);

    CustomerData customerEntityToData(Customer customer);
    Customer customerDataToEntity(CustomerData customerData);

    ReservationData reservationEntityToData(Reservation reservation);
    Reservation reservationDataToEntity(ReservationData reservationData);


}