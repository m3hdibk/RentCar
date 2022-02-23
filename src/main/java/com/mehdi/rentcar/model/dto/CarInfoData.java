package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarInfoData implements Serializable {


    private static final long serialVersionUID = -6907266532965468602L;
    private long id;
    private SchemeData make;
    private SchemeData model;
    private SchemeData type;
    private List<SchemeData> options;
    private int year;
    private SchemeData fuelType;
    private SchemeData transmission;
    private int tankSize;

}
