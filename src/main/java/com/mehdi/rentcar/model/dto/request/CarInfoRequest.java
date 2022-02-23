package com.mehdi.rentcar.model.dto.request;

import com.mehdi.rentcar.model.dto.SchemeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarInfoRequest implements Serializable {


    private static final long serialVersionUID = -7931387801450941645L;

    private long id;
    private long make;
    private long model;
    private long type;
    private List<Long> options;
    private int year;
    private long fuelType;
    private long transmission;
    private int tankSize;
}
