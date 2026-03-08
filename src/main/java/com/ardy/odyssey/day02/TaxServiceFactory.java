package com.ardy.odyssey.day02;

import com.ardy.odyssey.day01.*;


public class TaxServiceFactory {

    public static TaxCalculator getService(Country country, String name) {
        return switch (country){
            case SINGAPORE -> new SingapureTaxService(name, "SG-VET");
            case INDONESIA -> new IndonesiaTaxService(name, "ID-VET");
        };
    }
}

