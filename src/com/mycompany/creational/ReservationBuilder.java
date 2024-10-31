package com.mycompany.creational;

/**
 * Clase que utiliza patrón creacional builder
 */
public class ReservationBuilder {
    private String serviceType;
    private boolean insurance;
    private boolean additionalServices;

    public ReservationBuilder setServiceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public ReservationBuilder addInsurance() {
        this.insurance = true;
        return this;
    }

    public ReservationBuilder addAdditionalServices() {
        this.additionalServices = true;
        return this;
    }

    public Reservation build() {
        // Aquí construiríamos la reserva completa
        return ReservationFactory.createReservation(serviceType);
    }
}