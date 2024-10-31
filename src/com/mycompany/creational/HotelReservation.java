package com.mycompany.creational;

/**
 * Clase utilizada en patrón creacional factory method
 */
public class HotelReservation extends Reservation {
    public HotelReservation() {
        this.type = "Hotel";
    }

    public void book() {
        System.out.println("Reservando un hotel...");
    }
}
