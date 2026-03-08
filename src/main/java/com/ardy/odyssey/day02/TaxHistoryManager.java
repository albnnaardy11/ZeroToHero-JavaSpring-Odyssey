package com.ardy.odyssey.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxHistoryManager {

    //Validate <double> bahwasaan nya list hanya berisi angka pajak!
    private final List<Double> history = new ArrayList<>();

    //HashMap: Memastika Nama & Total Pajak pernah dihitung
    private final Map<String, Double> summaryMap = new HashMap<>();

    public void addRecord(String name, double amount) {
        history.add(amount);
        //Mengamukulasi total pajak per user
        summaryMap.put(name, summaryMap.getOrDefault(name, 0.0) + amount);
    }

    public void printLaporan(){
        System.out.println("\n-- LAPORAN AKHIR SESI ---");
        System.out.println("Total Transaksi: " + history.size());
        summaryMap.forEach((name, total) ->
                System.out.println("User: " + name + " | Total Pajak: " + total));
    }
}
