package com.mehdi.rentcar.service.impl;

import com.mehdi.rentcar.exception.InputValidationException;
import com.mehdi.rentcar.model.Car;
import com.mehdi.rentcar.model.CarInfo;
import com.mehdi.rentcar.model.Scheme;
import com.mehdi.rentcar.model.dto.CarData;
import com.mehdi.rentcar.model.dto.request.CarInfoRequest;
import com.mehdi.rentcar.repository.CarInfoRepository;
import com.mehdi.rentcar.service.CarInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    private final CarInfoRepository carInfoRepository;

    public CarInfoServiceImpl(CarInfoRepository carInfoRepository) {
        this.carInfoRepository = carInfoRepository;
    }

    @Override
    public long createCarInfo(CarInfoRequest carInfoRequest) {
        CarInfo carInfo = new CarInfo();

        getCarInfo(carInfoRequest, carInfo);


        CarInfo carInfoEntity = carInfoRepository.save(carInfo);
        return carInfoEntity.getId();
    }



    @Override
    public void updateCarInfo(CarInfoRequest carInfoRequest) {
        Optional<CarInfo> optionalCarInfo = carInfoRepository.findById(carInfoRequest.getId());
        CarInfo carInfo =  optionalCarInfo
                .orElseThrow(() -> new InputValidationException(List.of("Invalid Car Info Id")));
        getCarInfo(carInfoRequest,carInfo);
        carInfoRepository.save(carInfo);

    }

    private void getCarInfo(CarInfoRequest carInfoRequest, CarInfo carInfo) {
        Scheme make = new Scheme();
        make.setId(carInfoRequest.getMake());

        Scheme model = new Scheme();
        model.setId(carInfoRequest.getModel());

        Scheme type = new Scheme();
        type.setId(carInfoRequest.getType());

        Scheme fuelType = new Scheme();
        fuelType.setId(carInfoRequest.getFuelType());

        Scheme transmission = new Scheme();
        transmission.setId(carInfoRequest.getTransmission());

        List<Scheme> optionsList = new ArrayList<>();
        carInfoRequest.getOptions().forEach(optionId -> {
            Scheme option = new Scheme();
            option.setId(optionId);
            optionsList.add(option);
        });


        carInfo.setMake(make);
        carInfo.setModel(model);
        carInfo.setType(type);
        carInfo.setOptions(optionsList);
        carInfo.setYear(carInfoRequest.getYear());
        carInfo.setFuelType(fuelType);
        carInfo.setTransmission(transmission);
        carInfo.setTankSize(carInfoRequest.getTankSize());
    }
}
