package com.mycompany.creational;

/**
 * Clase que utiliza patrón creacional singleton
 */
public class ReservationSystem {
    private static ReservationSystem instance;

    private ReservationSystem() {}

    public static ReservationSystem getInstance() {
        if (instance == null) {
            instance = new ReservationSystem();
        }
        return instance;
    }
}
