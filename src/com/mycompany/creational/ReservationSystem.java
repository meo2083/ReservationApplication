package com.mycompany.creational;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que utiliza patrón creacional singleton
 */
public class ReservationSystem {
    private static ReservationSystem instance;

    private List<Reservation> reservation = new ArrayList<>();

    private ReservationSystem() {}

    /**
     * Método que devuelve instancia única de sistema de reservas
     * @return instancia de clase Reservation
     */
    public static ReservationSystem getInstance() {
        if (instance == null) {
            instance = new ReservationSystem();
        }
        return instance;
    }

    /**
     * Obtiene la lista de reservas del sistema
     * @return Lista de reservas del sistema
     */
    public List<Reservation> getReservation() {
        return reservation;
    }

    /**
     * Actualiza lista de reservas del sistema
     * @param reservation Lista de reservas
     */
    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }
}
