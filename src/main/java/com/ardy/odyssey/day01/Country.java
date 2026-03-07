package com.ardy.odyssey.day01;

public enum Country {
    SINGAPORE(1),
    INDONESIA(2);

    private final int value;

    Country(int value){
        this.value = value;
    }

    public static Country formInt(int choices){
        for (Country c : Country.values()) {
            if (c.value == choices) return c;
        }
        throw new IllegalArgumentException("Negara Tidak Ditemukan!");
    }
}
