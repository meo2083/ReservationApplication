package com.mycompany.structural;

import com.mycompany.creational.Reservation;

/**
 * Clase que utiliza patrón estructural Adapter
 */
public class ExternalServiceAdapter extends Reservation {
    private ExternalService externalService;

    public ExternalServiceAdapter(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public void book() {
        externalService.bookExternal();
    }
}
