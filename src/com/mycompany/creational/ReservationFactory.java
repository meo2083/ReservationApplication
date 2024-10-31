package com.mycompany.creational;

/**
 * Clase que utiliza patrón creacional factory method
 */
public class ReservationFactory {
    public static Reservation createReservation(String type) {
        switch (type) {
            case "hotel":
                return new HotelReservation();
            case "vuelo":
                return new FlightReservation();
            default:
                throw new IllegalArgumentException("Tipo de reservación desconocida");
        }
    }
}
