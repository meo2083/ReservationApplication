package com.mycompany.behavior;

import java.math.BigDecimal;

/**
 * Clase que implementa patrón de compartamiento Strategy para forma de pago tarjeta de crédito
 */
public class CreditCardPayment implements PaymentStrategy {

    private BigDecimal amount;

    public  CreditCardPayment(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void pay(BigDecimal amount)
    {
        this.amount = amount;
        System.out.println("Pago " + amount + " usando tarjeta de crédito.");
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "amount=" + amount +
                '}';
    }
}
