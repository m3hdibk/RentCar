package com.mehdi.rentcar.service.impl;

import com.mehdi.rentcar.exception.InputValidationException;
import com.mehdi.rentcar.mapper.Mapper;
import com.mehdi.rentcar.model.Car;
import com.mehdi.rentcar.model.CarInfo;
import com.mehdi.rentcar.model.Scheme;
import com.mehdi.rentcar.model.dto.request.CarInfoRequest;
import com.mehdi.rentcar.model.dto.request.CarRequest;
import com.mehdi.rentcar.model.dto.response.CarResponse;
import com.mehdi.rentcar.model.dto.response.PageResponse;
import com.mehdi.rentcar.repository.CarRepository;
import com.mehdi.rentcar.service.CarInfoService;
import com.mehdi.rentcar.service.CarService;
import com.mehdi.rentcar.validator.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final Mapper mapper;
    private final CarInfoService carInfoService;
    private final Validator validator;

    public CarServiceImpl(CarRepository carRepository, Mapper mapper, CarInfoService carInfoService,
                          Validator validator) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.carInfoService = carInfoService;
        this.validator = validator;
    }

    @Override
    public void createCar(CarRequest carRequest) {
        List<String> errors = validator.validateCarRequest(carRequest);
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        Car car = new Car();

        CarInfoRequest carInfoRequest = carRequest.getCarInfo();
        long carInfoId = carInfoService.createCarInfo(carInfoRequest);
        getCarEntity(carRequest, car, carInfoId);
        carRepository.save(car);
    }



    @Override
    public PageResponse<CarResponse> getAllCars(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Car> pageCar = carRepository.findAll(paging);

        List<CarResponse> products = pageCar.getContent().stream()
                .map(mapper::carEntityToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(products, pageCar.getNumber(),
                pageCar.getTotalElements(),
                pageCar.getTotalPages());
    }

    @Override
    public void updateCar(CarRequest carRequest) {
        Optional<Car> optionalCar = carRepository.findById(carRequest.getId());
        Car car = optionalCar
                .orElseThrow(() -> new InputValidationException(List.of("Invalid Car Id")));
        List<String> errors = validator.validateCarRequest(carRequest);
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        carInfoService.updateCarInfo(carRequest.getCarInfo());
        getCarEntity(carRequest, car, carRequest.getCarInfo().getId());
        carRepository.save(car);

    }


    private void getCarEntity(CarRequest carRequest, Car car, long carInfoId) {
        Scheme status = new Scheme();
        status.setId(carRequest.getStatus());

        Scheme color = new Scheme();
        color.setId(carRequest.getColor());

        CarInfo carInfo = new CarInfo();
        carInfo.setId(carInfoId);

        car.setVin(carRequest.getVin());
        car.setStatus(status);
        car.setColor(color);
        car.setCurrentOdometer(carRequest.getCurrentOdometer());
        car.setPlateNumber(carRequest.getPlateNumber());
        car.setCarInfo(carInfo);
    }
}
