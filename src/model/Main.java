package model;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:ss");

        System.out.println( " Enter rental data: ");
        System.out.println( "Car model:");
        String carModel = sc.nextLine();
        System.out.println( "Pick-up (dd/MM/yyyy HH:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.println( "Return (dd/MM/yyyy HH:ss): ");
        Date finish = sdf.parse(sc.nextLine());

        CarRental cr = new CarRental(start,finish,new Vehicle(carModel));

        System.out.println("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalServices rentalServices = new RentalServices(pricePerDay, pricePerHour,new BrazilTaxServices());

        rentalServices.processInvoice(cr);

        System.out.println("INVOICE: ");
        System.out.println( "Basic Payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println( "tax:  " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println( "Total Payment: " + String.format("%.2f", cr.getInvoice().TotalPayment()));

        sc.close();
    }
}
