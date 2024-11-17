package com.mycompany.behavior;

import java.math.BigDecimal;

/**
 * Clase que implementa patrón de comportamiento Staretegy para forma de pago PayPal
 */
public class PayPalPayment implements PaymentStrategy {

    private BigDecimal amount;

    public  PayPalPayment(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void pay(BigDecimal amount)
    {
        this.amount = amount;
        System.out.println("Pago " + amount + " usando PayPal.");
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "PayPalPayment{" +
                "amount=" + amount +
                '}';
    }
}