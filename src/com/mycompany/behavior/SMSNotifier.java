package com.mycompany.behavior;

/**
 * Clase que implementa patrón de comportamiento Observer
 */
public class SMSNotifier implements Observer{
    @Override
    public void update(String message) {
        System.out.println("Notificación por SMS: " + message);
    }

    @Override
    public String toString() {
        return "SMSNotifier{}";
    }
}
