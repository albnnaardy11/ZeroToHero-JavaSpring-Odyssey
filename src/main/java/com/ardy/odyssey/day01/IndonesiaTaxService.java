package com.ardy.odyssey.day01;


public class IndonesiaTaxService extends TaxPayer implements TaxCalculator{

    public IndonesiaTaxService(String name, String taxId){
        super(name, taxId);
    }

    @Override
    public double calculate(TaxRequest request){
        double netIncome = request.income() - request.expense();

        if (netIncome <= 60000000){
            return netIncome * 0.05;
        }else {
            return (60000000 * 0.05) + ((netIncome - 60000000) * 0.15);
        }
    }
}
