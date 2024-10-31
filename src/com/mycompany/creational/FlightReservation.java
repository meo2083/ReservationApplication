package com.mycompany.creational;

/**
 * Clase utilizada en patrón creacional factory method
 */
public class FlightReservation extends Reservation {
    public FlightReservation() {
        this.type = "Vuelo";
    }

    public void book() {
        System.out.println("Reservando un Vuelo...");
    }
}
