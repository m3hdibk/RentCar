package com.mehdi.rentcar.model.dto.response;

import com.mehdi.rentcar.model.dto.IdValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarResponse implements Serializable {


    private static final long serialVersionUID = -4882702106677763192L;

    private long id;
    private String vin;
    private IdValueObject status;
    private IdValueObject color;
    private long currentOdometer;
    private String plateNumber;
    private CarInfoResponse carInfo;
}
