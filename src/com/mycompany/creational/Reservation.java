package com.mycompany.creational;

import com.mycompany.behavior.Observer;
import com.mycompany.behavior.PaymentStrategy;

import java.util.List;
import java.util.Map;

/**
 * Clase utilizada para reservaciones
 */
public abstract class Reservation {
    protected String type;
    protected boolean insurance;
    protected boolean additionalServices;
    protected boolean cancelled;
    protected List<PaymentStrategy> payment;
    protected List<Observer> observer;

    public abstract void book();

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getType() {
        return type;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public boolean isAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(boolean additionalServices) {
        this.additionalServices = additionalServices;
    }

    public List<PaymentStrategy> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentStrategy> payment) {
        this.payment = payment;
    }

    public List<Observer> getObserver() {
        return observer;
    }

    public void setObserver(List<Observer> observer) {
        this.observer = observer;
    }

    @Override
    public String toString() {
        return "\nReservation{" +
                "\ntype='" + type + '\'' +
                ", \ninsurance=" + insurance +
                ", \nadditionalServices=" + additionalServices +
                ", \ncancelled=" + cancelled +
                ", \npayment=" + payment +
                ", \nobserver=" + observer +
                '}';
    }
}

