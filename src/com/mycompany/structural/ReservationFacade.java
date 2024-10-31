package com.mycompany.structural;

import com.mycompany.creational.Reservation;
import com.mycompany.creational.ReservationFactory;

/**
 * Clase que utiliza patrón estructural facade
 */
public class ReservationFacade {
    public void makeReservation(String type) {
        Reservation reservation = ReservationFactory.createReservation(type);
        reservation.book();
    }
}

