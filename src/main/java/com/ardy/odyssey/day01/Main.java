package com.ardy.odyssey.day01;

import java.util.*;
import java.text.NumberFormat;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n=== Tax System v2.0 ===");
                System.out.println("1. Singapore\n2. Indonesia\n0. Exit");
                System.out.print("Pilih Menu: ");

                int pilihan = scanner.nextInt();
                if (pilihan == 0) {
                    running = false;
                    continue;
                }
                scanner.nextLine(); // Clear buffer

                System.out.print("Masukan Nama: ");
                String name = scanner.nextLine();

                System.out.print("Masukan Pendapatan Tahunan: ");
                double income = scanner.nextDouble();

                System.out.print("Total Pengeluaran: ");
                double exp = scanner.nextDouble();

                Country selectedCountry = Country.formInt(pilihan);

                // Mengambil Service dari Factory
                TaxCalculator service = TaxServiceFactory.getService(selectedCountry, name);

                // Mengatur Format Mata Uang
                Locale locale = (pilihan == 1) ? new Locale("en", "SG") : new Locale("id", "ID");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

                // Menghitung Pajak
                double taxResult = service.calculate(new TaxRequest(income, exp));

                System.out.println("\n--- Hasil Perhitungan ---");
                System.out.println("Nama        : " + name);
                ((TaxPayer) service).displayInfo(); // Casting agar displayInfo bisa dipanggil
                System.out.println("Total Pajak : " + formatter.format(taxResult));

                // Simpan ke CSV
                saveToCSV(name, income, taxResult, formatter.format(taxResult));

            } catch (InputMismatchException e) {
                System.err.println("Error: Mohon masukkan angka yang valid!");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Sistem Ditutup. Sampai jumpa!");
        scanner.close();
    }

    private static void saveToCSV(String name, double inc, double tax, String formattedTax) {
        try (FileWriter fw = new FileWriter("audit_pajak.csv", true)) {
            String log = String.format("%s,%s,%.2f,%s\n", new Date(), name, inc, formattedTax);
            fw.write(log);
            System.out.println("[SYSTEM]: Data tersimpan di audit_pajak.csv");
        } catch (IOException e) {
            System.err.println("Gagal mencatat log.");
        }
    }
}