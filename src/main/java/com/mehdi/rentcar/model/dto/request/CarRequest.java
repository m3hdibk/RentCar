package com.mehdi.rentcar.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarRequest implements Serializable {

    private static final long serialVersionUID = -7891001564929820655L;

    private long id;
    private String vin;
    private long status;
    private long color;
    private long currentOdometer;
    private String plateNumber;
    private CarInfoRequest carInfo;
}
