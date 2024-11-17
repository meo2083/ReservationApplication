package com.mycompany.creational;

/**
 * Clase utilizada en patrón creacional Factory Method para reserva de vuelo
 */
public class FlightReservation extends Reservation {
    public FlightReservation() {
        this.type = "Vuelo";
    }

    private FlightReservation(FlightReservationBuilder builder)
    {
        this.type = builder.type;
        this.insurance = builder.insurance;
        this.additionalServices = builder.additionalServices;
    }

    public void book() {
        System.out.println("Reservando un Vuelo...");
    }

    public static class FlightReservationBuilder
    {
        private final String type;
        private boolean insurance;
        private boolean additionalServices;

        public FlightReservationBuilder(String type)
        {
            this.type = type;
        }

        public FlightReservationBuilder addInsurance()
        {
            System.out.println("Seguro añadido");
            this.insurance = true;
            return this;
        }

        public FlightReservationBuilder addAdditionalServices()
        {
            System.out.println("Servicios adicionales añadidos");
            this.additionalServices = true;
            return this;
        }

        public FlightReservation build()
        {
            return new FlightReservation(this);
        }
    }

}
