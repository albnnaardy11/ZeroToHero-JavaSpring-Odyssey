package com.ardy.odyssey.day02;

import com.ardy.odyssey.day01.*;
import com.ardy.odyssey.day01.SingapureTaxService;
import com.ardy.odyssey.day01.IndonesiaTaxService;
import com.ardy.odyssey.day01.TaxCalculator;


public class TaxServiceFactory {

    public static TaxCalculator getService(Country country, String name) {
        return switch (country){
            case SINGAPORE -> new SingapureTaxService(name, "SG-VET");
            case INDONESIA -> new IndonesiaTaxService(name, "ID-VET");
        };
    }
}

