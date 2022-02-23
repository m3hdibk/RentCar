package com.mehdi.rentcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car implements Serializable {


    private static final long serialVersionUID = 1592337607276373180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vin;
    @ManyToOne
    private Scheme status;
    @ManyToOne
    private Scheme color;
    private long currentOdometer;
    private String plateNumber;
    @ManyToOne
    private CarInfo carInfo;

}
