package com.ardy.odyssey.day03.model;

public class Kamera extends ItemSewa{
    private String tipeSensor;

    public Kamera() {}

    public Kamera(String id, String merk, Double hargaPerHari, String tipeSensor) {
        super(id, merk, hargaPerHari);
        this.tipeSensor = tipeSensor;
    }

    public String getTipeSensor() { return tipeSensor; }

    @Override
    public void tampilkanDetail(){
        System.out.println("[" + getId() + "] Merk: " + getMerk() +
        " | Sensor: " + tipeSensor +
        " | Harga: " + getHargaPerHari() + "/hari");
    }
}
