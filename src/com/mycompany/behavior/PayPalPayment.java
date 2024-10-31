package com.mycompany.behavior;

/**
 * Clase que implementa patrón de comportamiento Staretegy para forma de pago PayPal
 */
public class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Pago " + amount + " usando PayPal.");
    }
}