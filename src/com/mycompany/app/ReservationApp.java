package com.mycompany.app;

import com.mycompany.behavior.*;
import com.mycompany.creational.Reservation;
import com.mycompany.creational.ReservationFactory;
import com.mycompany.structural.InsuranceDecorator;
import com.mycompany.structural.ReservationFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase utilizada para ejecución de sistema de reservas utilizando patrones de diseño
 */
public class ReservationApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReservationFacade facade = new ReservationFacade();
        List<Observer> observers = new ArrayList<>();

        // Agregando un observador (por ejemplo, un notificador)
        observers.add(new EmailNotifier()); // Suponiendo que EmailNotifier implementa Observer
        observers.add(new SMSNotifier()); // Suponiendo que SMSNotifier implementa Observer

        System.out.println("Bievenido a sistema de reservas!");

        // Crear una reserva
        System.out.print("Seleccionar tipo de reserva (hotel/vuelo): ");
        String serviceType = scanner.nextLine();

        Reservation reservation = ReservationFactory.createReservation(serviceType);

        // Agregar seguro u otros servicios adicionales
        System.out.print("Desea añadir seguro? (si/no): ");
        String insuranceInput = scanner.nextLine();
        if (insuranceInput.equalsIgnoreCase("si")) {
            reservation = new InsuranceDecorator(reservation);
        }

        // Crear el comando para la reserva
        ReservationCommand command = new ReservationCommand(reservation);

        // Hacer la reserva
        command.execute();

        // Elegir método de pago
        System.out.print("Seleccione un método de pago (tarjeta/paypal): ");
        String paymentMethod = scanner.nextLine();
        PaymentStrategy paymentStrategy;

        if (paymentMethod.equalsIgnoreCase("tarjeta")) {
            paymentStrategy = new CreditCardPayment();
        } else if (paymentMethod.equalsIgnoreCase("paypal")) {
            paymentStrategy = new PayPalPayment();
        } else {
            System.out.println("Método de pago no válido. Saliendo.");
            return;
        }

        // Procesar el pago
        System.out.print("Ingrese el monto del pago: ");
        int amount = Integer.parseInt(scanner.nextLine());
        paymentStrategy.pay(amount);

        // Notificar a los usuarios sobre el estado de la reserva
        String notificationMessage = "Su reservación para " + serviceType + " ha sido confirmada.";
        for (Observer observer : observers) {
            observer.update(notificationMessage);
        }

        // Opción para deshacer la reserva
        System.out.print("Desea cancelar la reservación? (si/no): ");
        String cancelInput = scanner.nextLine();
        if (cancelInput.equalsIgnoreCase("si")) {
            command.undo();
        }

        scanner.close();
    }
}