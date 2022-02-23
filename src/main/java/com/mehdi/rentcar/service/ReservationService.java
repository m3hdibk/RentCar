package com.mehdi.rentcar.service;

import com.mehdi.rentcar.model.dto.request.ReservationRequest;

public interface ReservationService {

    long createReservation(ReservationRequest reservationRequest);

}
