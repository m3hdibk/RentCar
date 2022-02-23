package com.mehdi.rentcar.validator;


import com.mehdi.rentcar.model.dto.request.CarInfoRequest;
import com.mehdi.rentcar.model.dto.request.CarRequest;
import com.mehdi.rentcar.util.ConstantsUtils.SchemeType;

import java.util.List;

public interface Validator {

    List<String> validateCarRequest(CarRequest carRequest);

    List<String> validateCarInfo(CarInfoRequest carInfoRequest);

    List<String> validateScheme(long schemeId, SchemeType schemeType);

}
