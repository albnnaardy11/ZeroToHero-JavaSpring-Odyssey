package com.ardy.odyssey.day02;

import com.ardy.odyssey.day01.TaxRequest;
import com.ardy.odyssey.day01.TaxCalculator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TaxHistoryManager manager = new TaxHistoryManager();
        boolean running = true;

        while (running){
            try {
                System.out.println("\n--- TAX SYSTEM v2.1 ---");
                System.out.println("1. Singapore | 2. Indonesia | 3.Keluar");
                System.out.println("Pilih: ");

                int input = sc.nextInt();
                if (input == 0) break;
                sc.nextLine();

                Country country = Country.formInt(input);

                System.out.println("Masukan Name: ");
                String name = sc.nextLine();
                System.out.println("Pendapatan: ");
                double inc = sc.nextDouble();

                TaxCalculator service = TaxServiceFactory.getService(country, name);
                double tax = service.calculate(new TaxRequest(inc, 0));

                manager.addRecord(name, tax);
                System.out.println("Pajak Terhitung: " + tax);

            }catch (Exception e){
                System.err.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
        manager.printLaporan();;
    }
}