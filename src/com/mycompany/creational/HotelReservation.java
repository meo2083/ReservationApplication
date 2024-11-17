package com.mycompany.creational;

/**
 * Clase utilizada en patrón creacional Factory Method para reserva de hotel
 */
public class HotelReservation extends Reservation {
    public HotelReservation() {
        this.type = "Hotel";
    }

    private HotelReservation(HotelReservationBuilder builder)
    {
        this.type = builder.type;
        this.insurance = builder.insurance;
        this.additionalServices = builder.additionalServices;
    }

    public void book() {
        System.out.println("Reservando un Hotel...");
    }

    public static class HotelReservationBuilder
    {
        private final String type;
        private boolean insurance;
        private boolean additionalServices;

        public HotelReservationBuilder(String type)
        {
            this.type = type;
        }

        public HotelReservationBuilder addInsurance()
        {
            System.out.println("Seguro añadido");
            this.insurance = true;
            return this;
        }

        public HotelReservationBuilder addAdditionalServices()
        {
            System.out.println("Servicios adicionales añadidos");
            this.additionalServices = true;
            return this;
        }

        public HotelReservation build()
        {
            return new HotelReservation(this);
        }
    }
}
