package com.mycompany.app;

import com.mycompany.behavior.*;
import com.mycompany.behavior.Observer;
import com.mycompany.creational.*;
import com.mycompany.exception.InvalidPaymentException;
import com.mycompany.structural.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Clase utilizada para ejecución de sistema de reservas utilizando patrones de diseño
 * 1. reservationSystem para aplica el patrón Singleton (contiene la lista de reservas creadas)
 * 2. El método makeReservation() utiliza el patrón de diseño Factory Method para crear las reservas
 * 3. El método addAdditionalServices(Reservation reservation) utiliza el patrón de diseño Builder para añadir servicios adicionales a una reservación
 * 4. El método addInsurance() utiliza el patrón de diseño Decorator para añadir seguro a la reservación
 * 5. El método handleCompleteReservation() utiliza el patrón de diseño Facade para realizar reservación de hotel y vuelo
 * 6. El método handleExternalReservation() utiliza el patrón de diseño Adapter para realizar una reservación con servicio externo
 * 7. Dentro del método handleReservationProcess() se utiliza el patrón de diseño Command para cancelar una reservación
 */
public class ReservationApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ReservationSystem reservationSystem = ReservationSystem.getInstance();
    private static final List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    handleReservation("hotel");
                    break;
                case "2":
                    handleReservation("vuelo");
                    break;
                case "3":
                    handleCompleteReservation();
                    break;
                case "4":
                    handleExternalReservation();
                    break;
                case "5":
                    showReservations();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n----- SISTEMA DE RESERVAS -----");
        System.out.println("1. RESERVA DE HOTEL");
        System.out.println("2. RESERVA DE VUELO");
        System.out.println("3. RESERVA DE HOTEL Y VUELO");
        System.out.println("4. RESERVAR CON SERVICIO EXTERNO");
        System.out.println("5. VER RESERVAS");
        System.out.println("6. SALIR");
        System.out.print("Selecciona una opción: ");
    }

    private static void handleReservation(String serviceType) {
        Reservation reservation = makeReservation(serviceType);
        reservation.book();
        handleReservationProcess(reservation);
    }

    private static void handleCompleteReservation() {
        ReservationFacade facadeReservation = new ReservationFacade();
        facadeReservation.makeCompleteReservation();

        Reservation hotelReservation = facadeReservation.getHotelReservation();
        System.out.println("----- DETALLES DE RESERVACION DE HOTEL -----");
        handleReservationProcess(hotelReservation);

        Reservation flightReservation = facadeReservation.getFlightReservation();
        System.out.println("----- DETALLES DE RESERVACION DE VUELO -----");
        handleReservationProcess(flightReservation);
    }

    private static void handleExternalReservation() {
        Reservation externalReservation = makeExternalReservation();
        externalReservation.book();
        handleReservationProcess(externalReservation);
    }

    private static void handleReservationProcess(Reservation reservation) {
        reservation = addAdditionalServices(reservation);
        addInsurance(reservation);
        makePayment(reservation);
        notify(reservation);
        //Patrón de diseño Command para anular reservación
        ReservationCommand reservationCommand = new ReservationCommand(reservation);
        if (askToCancelReservation()) {
            reservationCommand.undo();
        }
        reservations.add(reservationCommand.getReservation());
        reservationSystem.setReservation(reservations);
    }

    private static boolean askToCancelReservation() {
        return askYesOrNo("Desea cancelar reserva");
    }

    //Crea reservación utilizando patrón de diseño Factory Method
    private static Reservation makeReservation(String serviceType) {
        ReservationFactory reservationFactory = new ReservationFactory();
        return reservationFactory.createReservation(serviceType);
    }

    //Crea reservación con servicio externo utilizando patrón de diseño Adapter
    private static Reservation makeExternalReservation() {
        ExternalBookingService externalService = new ExternalBookingService();
        return new ExternalServiceAdapter(externalService);
    }

    //Añade servicio adicional utilizando patrón de diseño builder para hotel y vuelo
    private static Reservation addAdditionalServices(Reservation reservation) {
        if (askYesOrNo("Desea añadir servicios adicionales")) {
            if (reservation instanceof FlightReservation) {
                reservation = new FlightReservation.FlightReservationBuilder("Vuelo")
                        .addAdditionalServices()
                        .build();
            } else if (reservation instanceof HotelReservation) {
                reservation = new HotelReservation.HotelReservationBuilder("Hotel")
                        .addAdditionalServices()
                        .build();
            }else if (reservation instanceof  ExternalServiceAdapter)
            {
                reservation.setAdditionalServices(true);
                System.out.println("Servicios adicionales añadidos");
            }
        }
        return reservation;
    }

    //Agrega seguro a reservación utilizando patrón de diseño Decorator
    private static void addInsurance(Reservation reservation) {
        if (askYesOrNo("Dese añadir seguro")) {
            reservation = new InsuranceDecorator(reservation);
            reservation.book();
        }
    }

    //Crea strategia de pago de reservación utilizando patrón de diseño Strategy
    private static void makePayment(Reservation reservation) {
        boolean validPayment = false;
        List<PaymentStrategy> paymentStrategies = new ArrayList<>();

        while (!validPayment) {
            System.out.println("Seleccione la forma de pago (T) Tarjeta, (P) PayPal, o (F) Finalizar pago:");
            String response = scanner.next().trim().toUpperCase();
            if ("T".equals(response) || "P".equals(response)) {
                System.out.println("Monto: ");
                try {
                    BigDecimal amount = scanner.nextBigDecimal();
                    scanner.nextLine();
                    if(amount.compareTo(BigDecimal.ZERO)<0)
                    {
                        throw new IllegalArgumentException("El monto debe ser mayor a cero");
                    }
                    PaymentStrategy paymentStrategy = createPaymentStrategy(response, amount);
                    paymentStrategies.add(paymentStrategy);
                }catch (InputMismatchException e)
                {
                    System.out.println("Monto no válido. Debe ingresar un número");
                    scanner.nextLine();
                }
                catch (IllegalArgumentException | InvalidPaymentException e) {
                    System.out.println("Error al procesar el pago: " + e.getMessage());
                }

            } else if ("F".equals(response)) {
                if (paymentStrategies.isEmpty()) {
                    System.out.println("No ha seleccionado ningún método de pago.");
                } else {
                    for (PaymentStrategy payment : paymentStrategies) {
                        payment.pay(payment.getAmount());
                    }
                    reservation.setPayment(paymentStrategies);
                    System.out.println("Pago procesado con éxito.");
                    validPayment = true;
                }
            } else {
                System.out.println("Opción no válida. Por favor seleccione una opción válida.");
            }
        }
    }

    //Notifica sobre reservación utilizando patrón de diseño Observer
    private static void notify(Reservation reservation) {
        boolean notify = false;
        String response = "";
        scanner.nextLine(); // Clear buffer

        while (!notify) {
            System.out.println("Seleccione método de notificación de reserva email (E), SMS (S), Ambos (A)");
            response = scanner.nextLine().trim();

            if (isValidNotificationResponse(response)) {
                notify = true;
            } else {
                System.out.println("Selecione un método de notificación válido 'E' para email, 'S' para SMS y 'A' para ambos");
            }
        }

        reservation.setObserver(createNotificationObservers(response));
        reservation.getObserver().forEach(observer -> observer.update("enviada"));
    }

    private static boolean isValidNotificationResponse(String response) {
        return "E".equalsIgnoreCase(response) || "S".equalsIgnoreCase(response) || "A".equalsIgnoreCase(response);
    }

    private static List<Observer> createNotificationObservers(String response) {
        List<Observer> observers = new ArrayList<>();
        if ("E".equalsIgnoreCase(response)) {
            observers.add(new EmailNotifier());
        } else if ("S".equalsIgnoreCase(response)) {
            observers.add(new SMSNotifier());
        } else {
            observers.add(new EmailNotifier());
            observers.add(new SMSNotifier());
        }
        return observers;
    }

    private static void showReservations() {
        if (reservationSystem.getReservation().isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("");
            // Imprimir encabezados
            System.out.printf("%-10s %-10s %-20s %-15s %-15s %-10s %-10s %-10s%n", "Tipo", "Seguro", "Servicio adicional", "Tarjeta", "PayPal", "Correo", "SMS", "Anulada");

            // Separador de la tabla
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            for(Reservation reservation:reservationSystem.getReservation())
            {
                BigDecimal creditCardAmount = BigDecimal.ZERO;
                BigDecimal payPalAmount = BigDecimal.ZERO;
                boolean notifyByEmail = false;
                boolean notiFyBySMS = false;
                if(!reservation.getPayment().isEmpty())
                {
                    for(PaymentStrategy paymentStrategy:reservation.getPayment())
                    {
                        if(paymentStrategy instanceof CreditCardPayment)
                        {
                            creditCardAmount = creditCardAmount.add(paymentStrategy.getAmount());
                        }
                        if(paymentStrategy instanceof PayPalPayment)
                        {
                            payPalAmount = payPalAmount.add(paymentStrategy.getAmount());
                        }
                    }
                }
                if(!reservation.getObserver().isEmpty())
                {
                    for(Observer observer:reservation.getObserver())
                    {
                        if(observer instanceof EmailNotifier)
                        {
                            notifyByEmail = true;
                        }
                        if(observer instanceof SMSNotifier)
                        {
                            notiFyBySMS = true;
                        }
                    }
                }
                System.out.printf("%-10s %-10s %-20s %-15.2f %-15.2f %-10s %-10s %-10s%n",reservation.getType(),showYesOrNo(reservation.isInsurance()),showYesOrNo(reservation.isAdditionalServices()),creditCardAmount,payPalAmount,showYesOrNo(notifyByEmail),showYesOrNo(notiFyBySMS),showYesOrNo(reservation.isCancelled()));
            }
        }
    }

    private static PaymentStrategy createPaymentStrategy(String paymentMethod, BigDecimal amount) throws InvalidPaymentException {
        switch (paymentMethod) {
            case "T":
                return new CreditCardPayment(amount);
            case "P":
                return new PayPalPayment(amount);
            default:
                throw new InvalidPaymentException("Método de pago no soportado.");
        }
    }

    private static boolean askYesOrNo(String message)
    {
        boolean validResponse = false;
        while (!validResponse)
        {
            System.out.println(message+" s/n:");
            String response = scanner.nextLine();
            if(!response.equalsIgnoreCase("s") && !response.equalsIgnoreCase("n")) {
                System.out.println("Debe seleccionar 's' para si o 'n' para no");
            }else
            {
                validResponse = true;
                return  "s".equalsIgnoreCase(response);
            }
        }
        return validResponse;
    }

    private static String showYesOrNo(boolean condition)
    {
        return condition?"SI":"NO";
    }
}
