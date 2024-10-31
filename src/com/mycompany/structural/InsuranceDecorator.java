package com.mycompany.structural;

import com.mycompany.creational.Reservation;

/**
 * Clase que utiliza patrón estructural Decorator
 */
public class InsuranceDecorator extends Reservation {
    private Reservation reservation;

    public InsuranceDecorator(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void book() {
        reservation.book();
        addInsurance();
    }

    private void addInsurance() {
        System.out.println("Seguro añadido.");
    }
}

