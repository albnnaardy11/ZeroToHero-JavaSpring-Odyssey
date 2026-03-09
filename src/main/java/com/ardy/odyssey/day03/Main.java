package com.ardy.odyssey.day03;

import com.ardy.odyssey.day03.service.LayananSewa;
import com.ardy.odyssey.day03.model.Kamera;

public class Main {
    public static void main(String[] args){

        Kamera cam1 = new Kamera("Cam-01", "Sony A7IV", 200.0, "Full Frame");
        Kamera cam2 = new Kamera("Cam-02", "Canon R6", 180.0, "Full Frame");
        LayananSewa layananSewa = new LayananSewa();

        layananSewa.tambahKameraKeStok(cam1);
        layananSewa.tambahKameraKeStok(cam2);

        System.out.println("--- Pendaftaran Antrian ---");
        layananSewa.tambahKeAntrian("Ardy");
        layananSewa.tambahKeAntrian("Budi");

        System.out.println("\n--- Proses Sewa ---");
        layananSewa.layananPenyewa();
        cam1.tampilkanDetail();

        int durasiSewa = 3;
        double totalBiaya = layananSewa.hitungTotal(cam1, durasiSewa);
        System.out.println("Total Biaya: $" + totalBiaya);

        // 5. Test Hapus Stok (Iterator)
        System.out.println("\n--- Update Inventaris ---");
        layananSewa.hapusKameraDariStok("Cam-01");
        
        layananSewa.layananPenyewa();
    }
}