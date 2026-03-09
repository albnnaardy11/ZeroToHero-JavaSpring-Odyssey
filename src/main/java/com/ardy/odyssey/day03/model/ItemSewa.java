package com.ardy.odyssey.day03.model;

public class ItemSewa {
    private String id;
    private String merk;
    private double hargaPerHari;

    public ItemSewa(String id, String merk, double hargaPerHari){
        this.id = id;
        this.merk = merk;
        this.hargaPerHari = hargaPerHari;
    }

    public String getId() { return id; }
    public String getMerk() { return merk; }
    public double getHargaPerHari() { return hargaPerHari; }
    public void tampilkanDetail() {
        System.out.println("[" + id + "] Merk: " + merk );
    }
}
