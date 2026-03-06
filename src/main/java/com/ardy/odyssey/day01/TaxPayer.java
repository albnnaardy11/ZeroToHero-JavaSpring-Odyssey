package com.ardy.odyssey.day01;

public abstract class TaxPayer {
    protected String name;
    protected String taxId;

    public TaxPayer(String name, String taxId){
        this.name = name;
        this.taxId = taxId;
    }

    public void displayInfo(){
        System.out.println("Tax Payer" + name + "[" + taxId +"]")    ;
    }
}
