package com.mycompany.behavior;

import com.mycompany.creational.Reservation;

/**
 * Clase que utiliza patrón de comportamiento command
 */
public class ReservationCommand {
    private Reservation reservation;

    public ReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }

    public void execute() {
        reservation.book();
    }

    public void undo() {
        System.out.println("Reservación cancelada.");
    }
}

