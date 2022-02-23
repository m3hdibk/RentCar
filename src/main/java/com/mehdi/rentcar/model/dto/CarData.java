package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarData implements Serializable {

    private static final long serialVersionUID = 4096864701953016071L;

    private long id;
    private String vin;
    private SchemeData status;
    private SchemeData color;
    private long currentOdometer;
    private String plateNumber;
    private CarInfoData carInfo;
}
