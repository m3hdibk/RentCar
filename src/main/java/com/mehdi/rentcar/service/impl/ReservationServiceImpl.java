package com.mehdi.rentcar.service.impl;

import com.mehdi.rentcar.mapper.Mapper;
import com.mehdi.rentcar.model.Car;
import com.mehdi.rentcar.model.Customer;
import com.mehdi.rentcar.model.Reservation;
import com.mehdi.rentcar.model.Scheme;
import com.mehdi.rentcar.model.dto.CarData;
import com.mehdi.rentcar.model.dto.CustomerData;
import com.mehdi.rentcar.model.dto.ReservationData;
import com.mehdi.rentcar.model.dto.SchemeData;
import com.mehdi.rentcar.model.dto.request.ReservationRequest;
import com.mehdi.rentcar.repository.CustomerRepository;
import com.mehdi.rentcar.repository.ReservationRepository;
import com.mehdi.rentcar.service.CustomerService;
import com.mehdi.rentcar.service.ReservationService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final Mapper mapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, Mapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }

    @Override
    public long createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();

        Car car = new Car();
        car.setId(reservationRequest.getCarId());
        reservation.setCar(car);

        Customer customer = new Customer();
        customer.setId(reservationRequest.getCustomerId());
        reservation.setCustomer(customer);

        Scheme statusScheme = new Scheme();
        statusScheme.setId(reservationRequest.getStatusId());
        reservation.setStatus(statusScheme);

        reservation.setCheckin(reservationRequest.getCheckin());
        reservation.setCheckout(reservationRequest.getCheckout());
        reservation.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Reservation reservationEntity =  reservationRepository.save(reservation);
        return reservationEntity.getId();

    }
}
