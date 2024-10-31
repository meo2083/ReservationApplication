package com.mycompany.behavior;

/**
 * Interfaz utilizada por patrón de compartamiento Strategy para implementar forma de pago
 */
public interface PaymentStrategy {
    void pay(int amount);
}
