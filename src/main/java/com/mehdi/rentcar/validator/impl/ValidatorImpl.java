package com.mehdi.rentcar.validator.impl;


import com.mehdi.rentcar.model.Scheme;
import com.mehdi.rentcar.model.dto.request.CarInfoRequest;
import com.mehdi.rentcar.model.dto.request.CarRequest;
import com.mehdi.rentcar.repository.CarRepository;
import com.mehdi.rentcar.repository.SchemeRepository;
import com.mehdi.rentcar.util.ConstantsUtils.SchemeType;
import com.mehdi.rentcar.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidatorImpl implements Validator {


    private final SchemeRepository schemeRepository;
    private final CarRepository carRepository;

    public ValidatorImpl(SchemeRepository schemeRepository, CarRepository carRepository) {
        this.schemeRepository = schemeRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<String> validateCarRequest(CarRequest carRequest) {
        List<String> errors = new ArrayList<>();
        if (carRequest.getVin().trim().isEmpty()) {
            errors.add("Invalid VIN Value");
        } else if (carRepository.existsByVin(carRequest.getVin())) {
            errors.add("VIN already existing");
        }
        errors.addAll(validateScheme(carRequest.getStatus(), SchemeType.CAR_STATUS));
        errors.addAll(validateScheme(carRequest.getColor(), SchemeType.CAR_COLOR));
        if (carRequest.getCurrentOdometer() < 0) {
            errors.add("Invalid Current Odometer value");
        }
        if (carRequest.getPlateNumber().trim().isEmpty()) {
            errors.add("Invalid Plate Number Value");
        }
        errors.addAll(validateCarInfo(carRequest.getCarInfo()));
        return errors;
    }

    @Override
    public List<String> validateCarInfo(CarInfoRequest carInfo) {
        List<String> errors = new ArrayList<>();
        errors.addAll(validateScheme(carInfo.getMake(), SchemeType.CAR_MAKE));
        errors.addAll(validateScheme(carInfo.getModel(), SchemeType.CAR_MODEL));
        errors.addAll(validateScheme(carInfo.getType(), SchemeType.CAR_TYPE));
        if (!carInfo.getOptions().isEmpty()) {
            carInfo.getOptions().forEach(schemeId ->
                    errors.addAll(validateScheme(schemeId, SchemeType.CAR_OPTION)));
        }
        errors.addAll(validateScheme(carInfo.getFuelType(), SchemeType.FUEL_TYPE));
        errors.addAll(validateScheme(carInfo.getTransmission(), SchemeType.CAR_TRANSMISSION));
        if (carInfo.getTankSize() <1) {
            errors.add("Invalid Tank Size Value");
        }
        if (carInfo.getYear() <1) {
            errors.add("Invalid Car Year Value");
        }
        return errors;
    }

    @Override
    public List<String> validateScheme(long schemeId, SchemeType schemeType) {
        List<String> errors = new ArrayList<>();
        if (schemeId == 0) {
            return List.of(schemeType.getName() + " is Invalid");
        }
        Optional<Scheme> optionalScheme = schemeRepository.findSchemesByIdAndStatusTrueAndType(schemeId,
                schemeType.getId());
        if (optionalScheme.isEmpty()) {
            errors.add("Invalid Id of " + schemeType.getName());
        }
        return errors;
    }
}
