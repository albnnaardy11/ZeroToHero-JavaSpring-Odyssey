package com.ardy.odyssey.day02;

import com.ardy.odyssey.day01.TaxRequest;
import com.ardy.odyssey.day01.TaxCalculator;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TaxHistoryManager manager = new TaxHistoryManager();
        DecimalFormat df = new DecimalFormat("#,###.##");

        boolean running = true;
        while (running){
            try {
                System.out.println("\n--- TAX SYSTEM v2.1 ---");
                System.out.println("1. Singapore | 2. Indonesia | 3. Keluar");
                System.out.print("Pilih: ");

                int input = sc.nextInt();
                if (input == 3) break;
                sc.nextLine();

                Country country = Country.fromInt(input);

                System.out.print("Masukan Name: ");
                String name = sc.nextLine();

                // MODIFIKASI DISINI: Baca sebagai String dulu supaya bisa dihapus titiknya
                System.out.print("Pendapatan : ");
                String rawInc = sc.nextLine();

                // Menghapus semua titik agar "72.000.000" jadi "72000000"
                double inc = Double.parseDouble(rawInc.replace(".", ""));

                TaxCalculator service = TaxServiceFactory.getService(country, name);

                if (service == null) {
                    System.err.println(" Error: Layanan tidak ditemukan.");
                    continue;
                }

                double tax = service.calculate(new TaxRequest(inc, 0));
                manager.addRecord(name, tax);

                String symbol = (country == Country.SINGAPORE) ? "s$ " : "Rp ";
                System.out.println(" Pajak Terhitung untuk " + name + ": " + symbol + df.format(tax));

            } catch (Exception e) {
                System.err.println(" Terjadi kesalahan: Input tidak valid");
                if (sc.hasNextLine()) sc.nextLine();
            }
        }
        manager.printLaporan();
    }
}