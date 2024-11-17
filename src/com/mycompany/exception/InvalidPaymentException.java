package com.mycompany.exception;

/**
 * Utilizada para manejo de excepciones en formas de pago
 */
public class InvalidPaymentException extends Exception{

    public InvalidPaymentException(String message) {
        super(message);
    }

}
