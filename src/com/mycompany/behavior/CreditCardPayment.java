package com.mycompany.behavior;

/**
 * Clase que implementa patrón de compartamiento Strategy para forma de pago tarjeta de crédito
 */
public class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Pago " + amount + " usando tarjeta de crédito.");
    }
}
