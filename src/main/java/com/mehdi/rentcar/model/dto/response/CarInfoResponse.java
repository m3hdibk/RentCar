package com.mehdi.rentcar.model.dto.response;

import com.mehdi.rentcar.model.dto.IdValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarInfoResponse implements Serializable {

    private static final long serialVersionUID = -4824359198777814345L;

    private long id;
    private IdValueObject make;
    private IdValueObject model;
    private IdValueObject type;
    private List<IdValueObject> options;
    private int year;
    private IdValueObject fuelType;
    private IdValueObject transmission;
    private int tankSize;
}
