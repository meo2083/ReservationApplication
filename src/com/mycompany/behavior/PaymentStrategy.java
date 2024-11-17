package com.mycompany.behavior;

import java.math.BigDecimal;

/**
 * Interfaz utilizada por patrón de compartamiento Strategy para implementar formas de pago
 */
public interface PaymentStrategy {
    void pay(BigDecimal amount);
    BigDecimal getAmount();
}
