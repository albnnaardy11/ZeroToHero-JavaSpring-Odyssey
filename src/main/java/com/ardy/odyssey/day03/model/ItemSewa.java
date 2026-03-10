package com.ardy.odyssey.day03.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Kamera.class, name = "kamera")
})

public abstract class ItemSewa {
    private String id;
    private String merk;
    private double hargPerHari;

    public ItemSewa() {}

    public ItemSewa(String id, String merk, double hargPerHari){
        this.id = id;
        this.merk = merk;
        this.hargPerHari = hargPerHari;
    }

    public String getId() { return id; }
    public String getMerk() { return merk; }
    public Double getHargaPerHari() { return hargPerHari; }

    public abstract void tampilkanDetail();
}