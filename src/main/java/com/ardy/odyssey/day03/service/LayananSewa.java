package com.ardy.odyssey.day03.service;

import com.ardy.odyssey.day03.model.ItemSewa;
import com.ardy.odyssey.day03.model.Kamera;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LayananSewa {

    private  Queue<String> antrianPenyewa = new LinkedList<>();
    private  Set<Kamera> daftarStok = new HashSet<>();

    private final String DATABASE_FILE = "stok_kamera.json";
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static LayananSewa instance;

    private LayananSewa(){
        muatDataDariFile();
    }

    public static synchronized  LayananSewa getInstance() {
        if (instance == null) instance = new LayananSewa();
        return instance;
    }

    public void tambahKeAntrian(String nama){
        antrianPenyewa.add(nama);
        System.out.println("[Queue] " + nama + " masuk antrian.");
    }

    public void layananPenyewa(int durasi) {
        String penyewa = antrianPenyewa.poll();

       if (penyewa == null){
            System.out.println("Antrian kosong!");
            return;
        }
       if (daftarStok.isEmpty()) {
           System.out.println("Maaf" + penyewa + ", stok kamera sedang kosong!");
           return;
       }

       Kamera kameraDipilih = daftarStok.iterator().next();
       double total = hitungTotal(kameraDipilih, durasi);

       System.out.println("\n--- TRANSAKSI BERHASIL ---");
       System.out.println("Pelanggan: " + penyewa);
       kameraDipilih.tampilkanDetail();
       System.out.println("Total Biaya (" + durasi + "hari): $" +total);

       hapusKameraDariStok(kameraDipilih.getId());
    }

    public void tambahKameraKeStok(Kamera kamera){
       if (daftarStok.add(kamera)) {
           System.out.println("Berhasil menambah: " + kamera.getMerk());
           simpanKeFile();
       } else {
           System.out.println("Kamera dengan ID tersbut sudah ada!");
       }
    }

    public void hapusKameraDariStok(String id) {
        Iterator<Kamera> it = daftarStok.iterator();
        boolean ditemukan = false;

        while (it.hasNext()) {
            Kamera k = it.next();
            if (k.getId().equals(id)) {
                it.remove();
                ditemukan = true;
                break;
            }
            if (ditemukan) {
                System.out.println("Kamera [" + id + "] dihapus dari stok");
                simpanKeFile();
            }
        }
    }
    public double hitungTotal(ItemSewa item, int durasi) {
    return item.getHargaPerHari() * durasi;
    }

    private void simpanKeFile() {
        try {
            mapper.writeValue(new File(DATABASE_FILE), daftarStok);
        } catch (IOException e){
            System.err.println("Gagal simpan ke JSON: " + e.getMessage());
        }
    }

    private void muatDataDariFile() {
        try {
            File file = new File(DATABASE_FILE);
            if (file.exists()) {
                com.fasterxml.jackson.core.type.TypeReference<HashSet<Kamera>> typeRef =
                        new com.fasterxml.jackson.core.type.TypeReference<>() {};
                daftarStok = mapper.readValue(file, typeRef);
                System.out.println("Data berhasil dimuat. Stok tersedia: " + daftarStok.size());
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat data, memulai database baru.");
        }
    }

}
