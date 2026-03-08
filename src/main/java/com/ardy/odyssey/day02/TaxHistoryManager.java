package com.ardy.odyssey.day02;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxHistoryManager {

    //Validate <double> bahwasaan nya list hanya berisi angka pajak!
    private final List<Double> history = new ArrayList<>();

    //HashMap: Memastika Nama & Total Pajak pernah dihitung
    private final Map<String, Double> summaryMap = new HashMap<>();

    private final DecimalFormat df = new DecimalFormat("#,###.##");

    public void addRecord(String name, double amount) {
        history.add(amount);
        //Mengamukulasi total pajak per user
        summaryMap.put(name, summaryMap.getOrDefault(name, 0.0) + amount);
    }

    public void printLaporan(){
        System.out.println("\n-- LAPORAN AKHIR SESI ---");
        System.out.println("Total Transaksi: " + history.size());
        summaryMap.forEach((name, total) -> {
            String currency = (total < 100000) ? "s$" : "Rp" ;
            System.out.println("User: " + String.format("%-10s", name) + " | Total Pajak: " + currency + df.format(total));

        });
    }
}
