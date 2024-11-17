package com.mycompany.creational;

/**
 * Clase que utiliza patrón creacional Factory Method para reserva de hotel o vuelo
 */
public class ReservationFactory {
    public static Reservation createReservation(String type) {
        switch (type.toLowerCase()) {
            case "hotel":
                return new HotelReservation();
            case "vuelo":
                return new FlightReservation();
            default:
                throw new IllegalArgumentException("Tipo de reservación desconocida");
        }
    }
}
