package com.mycompany.behavior;

/**
 * Clase implementado patrón de comportamiento Observer
 */
public class EmailNotifier implements Observer{
    @Override
    public void update(String message) {
        System.out.println("Notificación por email: " + message);
    }

    @Override
    public String toString() {
        return "EmailNotifier{}";
    }
}
