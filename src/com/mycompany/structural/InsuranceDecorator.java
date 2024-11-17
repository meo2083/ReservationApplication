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

    /**
     * Invoca método que añade seguro a reservación
     */
    @Override
    public void book() {
        addInsurance();
    }

    /**
     * Añade seguro a reservación
     */
    private void addInsurance() {
        this.reservation.setInsurance(true);
        System.out.println("Seguro añadido.");
    }


}

