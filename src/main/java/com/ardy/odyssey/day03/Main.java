package com.ardy.odyssey.day03;

import com.ardy.odyssey.day03.service.LayananSewa;
import com.ardy.odyssey.day03.model.Kamera;

public class Main {
    public static void main(String[] args){

        Kamera cam = new Kamera("Cam-01", "Sony A7IV", 200.00, "Full Frame");

        LayananSewa layananSewa = new LayananSewa();

        System.out.println("--- Detail Barang ---");
        cam.tampilkanDetail();

        int durasiSewa = 5;
        double totalBiaya = layananSewa.hitungTotal(cam, durasiSewa);

        System.out.println("\n--- Ringkasan Sewa ---");
        System.out.println("Durasi: " + durasiSewa + "hari");
        System.out.println("Total yang dibayar: $" + totalBiaya);
    }
}