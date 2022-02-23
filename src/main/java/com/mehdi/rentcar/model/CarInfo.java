package com.mehdi.rentcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "carInfos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarInfo implements Serializable {


    private static final long serialVersionUID = -866882327029974381L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Scheme make;
    @ManyToOne
    private Scheme model;
    @ManyToOne
    private Scheme type;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Scheme> options;
    private int year;
    @ManyToOne
    private Scheme fuelType;
    @ManyToOne
    private Scheme transmission;
    private int tankSize;
}
