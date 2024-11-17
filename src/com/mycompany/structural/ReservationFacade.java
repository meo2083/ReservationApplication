package com.mycompany.structural;

import com.mycompany.creational.FlightReservation;
import com.mycompany.creational.HotelReservation;
import com.mycompany.creational.Reservation;
import com.mycompany.creational.ReservationFactory;

/**
 * Clase que utiliza patrón estructural Facade para reserva de hotel y vuelo
 */
public class ReservationFacade {
    private HotelReservation hotelReservation;
    private FlightReservation flightReservation;

    public ReservationFacade()
    {
        this.hotelReservation = new HotelReservation();
        this.flightReservation = new FlightReservation();
    }

    /**
     * Reserva completa de hotel y vuelo
     */
    public void makeCompleteReservation() {
        hotelReservation.book();
        flightReservation.book();
    }

    public HotelReservation getHotelReservation() {
        return hotelReservation;
    }

    public void setHotelReservation(HotelReservation hotelReservation) {
        this.hotelReservation = hotelReservation;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }
}

