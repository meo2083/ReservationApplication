package com.mycompany.structural;

/**
 * Clase que será utilizada en patrón estructural Adapter
 */
public class ExternalBookingService implements ExternalService {
    public void bookExternal() {
        System.out.println("Reservando con servicio externo...");
    }
}
