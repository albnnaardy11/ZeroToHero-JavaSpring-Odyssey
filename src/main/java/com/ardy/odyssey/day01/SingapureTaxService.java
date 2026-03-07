package com.ardy.odyssey.day01;

import com.ardy.odyssey.day01.TaxCalculator;
import com.ardy.odyssey.day01.TaxPayer;

public class SingapureTaxService extends  TaxPayer implements TaxCalculator{

    public SingapureTaxService(String name, String taxId) {
        super(name, taxId);
    }

    @Override
    public double calculate(double income) {
        if (income <=20000){
            return 0;
        } else if (income <= 30000) {
            return (income - 20000) * 0.02;
        }else {
            return 200 + ((income -30000) * 0.035);
        }
    }
}

