package com.mehdi.rentcar.mapper.impl;

import com.mehdi.rentcar.mapper.Mapper;
import com.mehdi.rentcar.model.*;
import com.mehdi.rentcar.model.dto.*;
import com.mehdi.rentcar.model.dto.response.CarInfoResponse;
import com.mehdi.rentcar.model.dto.response.CarResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {

    @Override
    public CarData carEntityToData(Car car) {
        CarData carData = new CarData();
        carData.setId(car.getId());
        carData.setVin(car.getVin());
        carData.setStatus(schemeEntityToData(car.getStatus()));
        carData.setColor(schemeEntityToData(car.getColor()));
        carData.setCurrentOdometer(car.getCurrentOdometer());
        carData.setPlateNumber(car.getPlateNumber());
        carData.setCarInfo(carInfoEntityToData(car.getCarInfo()));
        return carData;
    }

    @Override
    public Car carDataToEntity(CarData carData) {
        Car car = new Car();
        car.setId(carData.getId());
        car.setVin(carData.getVin());
        car.setStatus(schemeDataToEntity(carData.getStatus()));
        car.setColor(schemeDataToEntity(carData.getColor()));
        car.setCurrentOdometer(carData.getCurrentOdometer());
        car.setPlateNumber(carData.getPlateNumber());
        car.setCarInfo(carInfoDataToEntity(carData.getCarInfo()));
        return car;
    }

    @Override
    public CarResponse carEntityToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setVin(car.getVin());
        carResponse.setStatus(schemeEntityToIdValue(car.getStatus()));
        carResponse.setColor(schemeEntityToIdValue(car.getColor()));
        carResponse.setCurrentOdometer(car.getCurrentOdometer());
        carResponse.setPlateNumber(car.getPlateNumber());
        carResponse.setCarInfo(carInfoEntityToResponse(car.getCarInfo()));
        return carResponse;
    }

    @Override
    public SchemeData schemeEntityToData(Scheme scheme) {
        SchemeData schemeData = new SchemeData();
        schemeData.setId(scheme.getId());
        schemeData.setName(scheme.getName());
        schemeData.setValue(scheme.getValue());
        schemeData.setDescription(scheme.getDescription());
        schemeData.setType(scheme.getType());
        schemeData.setStatus(scheme.isStatus());
        schemeData.setDefaultItem(scheme.isDefaultItem());
        return schemeData;
    }

    @Override
    public Scheme schemeDataToEntity(SchemeData schemeData) {
        Scheme scheme = new Scheme();
        scheme.setId(schemeData.getId());
        scheme.setName(schemeData.getName());
        scheme.setValue(schemeData.getValue());
        scheme.setDescription(schemeData.getDescription());
        scheme.setType(schemeData.getType());
        scheme.setStatus(schemeData.isStatus());
        scheme.setDefaultItem(schemeData.isDefaultItem());
        return scheme;
    }

    @Override
    public IdValueObject schemeEntityToIdValue(Scheme scheme) {
        return new IdValueObject(scheme.getId(), scheme.getValue());
    }

    @Override
    public CarInfoData carInfoEntityToData(CarInfo carInfo) {
        CarInfoData carInfoData = new CarInfoData();
        carInfoData.setId(carInfo.getId());
        carInfoData.setMake(schemeEntityToData(carInfo.getMake()));
        carInfoData.setModel(schemeEntityToData(carInfo.getModel()));
        carInfoData.setType(schemeEntityToData(carInfo.getType()));
        carInfoData.setOptions(carInfo.getOptions()
                .stream()
                .map(this::schemeEntityToData)
                .collect(Collectors.toList()));
        carInfoData.setYear(carInfo.getYear());
        carInfoData.setFuelType(schemeEntityToData(carInfo.getFuelType()));
        carInfoData.setTransmission(schemeEntityToData(carInfo.getTransmission()));
        carInfoData.setTankSize(carInfo.getTankSize());
        return carInfoData;
    }

    @Override
    public CarInfo carInfoDataToEntity(CarInfoData carInfoData) {
        CarInfo carInfo = new CarInfo();
        carInfo.setId(carInfoData.getId());
        carInfo.setMake(schemeDataToEntity(carInfoData.getMake()));
        carInfo.setModel(schemeDataToEntity(carInfoData.getModel()));
        carInfo.setType(schemeDataToEntity(carInfoData.getType()));
        carInfo.setOptions(carInfoData.getOptions()
                .stream()
                .map(this::schemeDataToEntity)
                .collect(Collectors.toList()));
        carInfo.setYear(carInfoData.getYear());
        carInfo.setFuelType(schemeDataToEntity(carInfoData.getFuelType()));
        carInfo.setTransmission(schemeDataToEntity(carInfoData.getTransmission()));
        carInfo.setTankSize(carInfoData.getTankSize());
        return carInfo;
    }

    @Override
    public CarInfoResponse carInfoEntityToResponse(CarInfo carInfo) {
        CarInfoResponse carInfoResponse = new CarInfoResponse();
        carInfoResponse.setId(carInfo.getId());
        carInfoResponse.setMake(schemeEntityToIdValue(carInfo.getMake()));
        carInfoResponse.setModel(schemeEntityToIdValue(carInfo.getModel()));
        carInfoResponse.setType(schemeEntityToIdValue(carInfo.getType()));
        carInfoResponse.setOptions(carInfo.getOptions()
                .stream()
                .map(this::schemeEntityToIdValue)
                .collect(Collectors.toList()));
        carInfoResponse.setYear(carInfo.getYear());
        carInfoResponse.setFuelType(schemeEntityToIdValue(carInfo.getFuelType()));
        carInfoResponse.setTransmission(schemeEntityToIdValue(carInfo.getTransmission()));
        carInfoResponse.setTankSize(carInfo.getTankSize());
        return carInfoResponse;
    }

    @Override
    public CustomerData customerEntityToData(Customer customer) {
        CustomerData customerData = new CustomerData();
        customerData.setId(customer.getId());
        customerData.setFullName(customer.getFullName());
        customerData.setPhone(customer.getPhone());
        customerData.setAddress(customer.getAddress());
        customerData.setEmail(customer.getEmail());
        customerData.setIdNumber(customer.getIdNumber());
        customerData.setNote(customer.getNote());
        return customerData;
    }

    @Override
    public Customer customerDataToEntity(CustomerData customerData) {
        Customer customer = new Customer();
        customer.setId(customerData.getId());
        customer.setFullName(customerData.getFullName());
        customer.setPhone(customerData.getPhone());
        customer.setAddress(customerData.getAddress());
        customer.setEmail(customerData.getEmail());
        customer.setIdNumber(customerData.getIdNumber());
        customer.setNote(customerData.getNote());
        return customer;
    }

    @Override
    public ReservationData reservationEntityToData(Reservation reservation) {
        return ReservationData.builder()
                .id(reservation.getId())
                .customer(customerEntityToData(reservation.getCustomer()))
                .car(carEntityToData(reservation.getCar()))
                .checkin(reservation.getCheckin())
                .checkout(reservation.getCheckout())
                .status(schemeEntityToData(reservation.getStatus()))
                .creationDate(reservation.getCreationDate())
                .build();
    }

    @Override
    public Reservation reservationDataToEntity(ReservationData reservationData) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationData.getId());
        reservation.setCustomer(customerDataToEntity(reservationData.getCustomer()));
        reservation.setCar(carDataToEntity(reservationData.getCar()));
        reservation.setCheckin(reservationData.getCheckin());
        reservation.setCheckout(reservationData.getCheckout());
        reservation.setStatus(schemeDataToEntity(reservationData.getStatus()));
        reservation.setCreationDate(reservationData.getCreationDate());
        return reservation;
    }
}
