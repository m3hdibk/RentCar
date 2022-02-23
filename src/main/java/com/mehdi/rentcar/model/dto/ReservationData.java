package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationData implements Serializable {


    private static final long serialVersionUID = -1302270786958869559L;
    private long id;
    private CustomerData customer;
    private CarData car;
    private Date checkin;
    private Date checkout;
    private SchemeData status;
    private Timestamp creationDate;

}
