package com.mehdi.rentcar.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation implements Serializable {


    private static final long serialVersionUID = 7555923804396649051L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Car car;
    private Date checkin;
    private Date checkout;
    @ManyToOne
    private Scheme status;
    private Timestamp creationDate;
}
