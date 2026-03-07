package com.ardy.odyssey.day01;

import com.ardy.odyssey.day01.SingapureTaxService;
import com.ardy.odyssey.day01.TaxCalculator;
import com.ardy.odyssey.day01.IndonesiTaxService;
import com.ardy.odyssey.day01.TaxRequest;

import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;


public class main {
    public static void  main(String[] args) {

        //1.Inisisasi Service
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Tax System ===");
            System.out.println("Pilih Negara(1: Singapore, 2: Indonesia):");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Masukan Nama: ");
            String name = scanner.nextLine();

            System.out.println("Masukan Pendapatan Tahunan: ");
            double income = scanner.nextDouble();

            System.out.println("Total Pengeluaran: ");
            double exp = scanner.nextDouble();

            //3.Membungkus data Tax
            TaxRequest request = new TaxRequest(income, exp);

            TaxCalculator service;
            NumberFormat currencyFormat;

            if (pilihan == 1){
                service = new SingapureTaxService(name, "SG-VET");
                currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "SG"));
            }else {
                service = new IndonesiTaxService(name, "ID-VET");
                currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            }

            double taxResult = service.calculate(request);

            System.out.println("\n--- Hasil Perhitungan ---");
            System.out.println("Nama : " + name);
            ((TaxPayer)service).displayInfo();

            System.out.println("Total Pajak: " + currencyFormat.format(taxResult));

            try(java.io.FileWriter writer = new java.io.FileWriter("audit_pajak.txt", true)) {
                String logEntry = String.format("[%s] User: %s | Income: %.2f| Tax: %s\n",
                        new java.util.Date(), name, income, currencyFormat.format(taxResult));

                writer.write(logEntry);
                System.out.println("\n[SYSTEM]: Data telah berhasil di backup ke audit_pajak.txt");
            }catch (java.io.IOException e){
                System.err.println("[SYSTEM ERROR]: Gagal menulis log ke file.");
            }

            System.out.println("\n[AUDIT]: Transaksi berhasil dicatat untuk user: " + name);

        } catch (Exception e){
            System.err.println("Error: Input harus berupa angka yang valid");
        } finally {
            scanner.close();
        }
    }
}