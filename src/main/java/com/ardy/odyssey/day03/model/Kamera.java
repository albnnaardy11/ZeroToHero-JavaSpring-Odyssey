package com.ardy.odyssey.day03.model;

public class Kamera extends ItemSewa{
    private String tipeSensor;

    public Kamera(String id, String merk, double hargaPerHari, String tipeSensor) {
        super(id, merk, hargaPerHari);
        this.tipeSensor = tipeSensor;
    }

    @Override
    public void tampilkanDetail() {
        super.tampilkanDetail();
        System.out.println("Sensor: " + tipeSensor + " | Harga $" + getHargaPerHari() + "/hari");
    }
}
