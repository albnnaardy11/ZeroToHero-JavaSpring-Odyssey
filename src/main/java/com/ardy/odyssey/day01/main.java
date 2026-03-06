package com.ardy.odyssey.day01;

import com.ardy.odyssey.day01.SingapureTaxService;
import java.util.Scanner;

public class main {
    public static void  main(String[] args){

        //1.Inisisasi Service
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Singapur Tax CLI ===");

        //2.Data Testing
        System.out.println("Masukan Nama Anda: ");
        String name = scanner.nextLine();

        System.out.println("Masukan Pendapatan Tahunan (SGD): ");
        double income = scanner.nextDouble();

        //3.Inisiasi Service
        SingapureTaxService calculator = new SingapureTaxService(name, "SG-123456");


        //4,Result Tax Sisytem
        double taxResult = calculator.calculate(income);

        System.out.println("\n--- Hasil Perhitungan ---");
        calculator.displayInfo();
        System.out.println("Annual Income: SGD" + income);
        System.out.println("Tax to Pay :  SGD" + taxResult);

        scanner.close();
    }
}