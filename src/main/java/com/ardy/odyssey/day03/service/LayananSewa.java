package com.ardy.odyssey.day03.service;

import com.ardy.odyssey.day03.model.ItemSewa;

public class LayananSewa {
    public double hitungTotal(ItemSewa item, int durasi){
        return item.getHargaPerHari() * durasi;
    }
}
