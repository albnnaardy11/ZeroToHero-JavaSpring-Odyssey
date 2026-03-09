package com.ardy.odyssey.day03.service;

import com.ardy.odyssey.day03.model.ItemSewa;
import com.ardy.odyssey.day03.model.Kamera;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LayananSewa {

    private  Queue<String> antrianPenyewa = new LinkedList<>();
    private  Set<Kamera> daftarStok = new HashSet<>();

    public void tambahKeAntrian(String nama){
        antrianPenyewa.add(nama);
        System.out.println("Masuk ke dalam antrian.");
    }

    public void layananPenyewa() {
        String penyewa = antrianPenyewa.poll();
        if (penyewa != null){
            System.out.println("Melayani penyewa: " + penyewa);
        } else {
            System.out.println("Antrian kosong!");
        }
    }

    public void tambahKameraKeStok(Kamera kamera){
        daftarStok.add(kamera);
    }

    public void hapusKameraDariStok(String id) {
        Iterator<Kamera> it = daftarStok.iterator();
        while (it.hasNext()) {
            Kamera k = it.next();
            if (k.getId().equals(id)) {
                it.remove();
                System.out.println("Kamera" + id + "dihapus dari stok.");

            }
        }
    }
    public double hitungTotal(ItemSewa item, int durasi){
    return item.getHargaPerHari() * durasi;
    }
}
