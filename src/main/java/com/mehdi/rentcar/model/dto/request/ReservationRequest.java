package com.mehdi.rentcar.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationRequest implements Serializable {


    private static final long serialVersionUID = 6873197653510189120L;

    private long id;
    private long customerId;
    private long carId;
    private Date checkin;
    private Date checkout;
    private long statusId;
}
