package com.ardy.odyssey.day02;

import com.ardy.odyssey.day01.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TaxCalculatorTest {

    @Test
    @DisplayName("Test Singapore Tax Calculation - Lower Bracket")
    void testSingaporeTaxLowerBracket() {
        TaxCalculator calculator = TaxServiceFactory.getService(Country.SINGAPORE, "John Doe");
        TaxRequest request = new TaxRequest(25000, 0);
        double tax = calculator.calculate(request);
        // Bracket: (25000 - 20000) * 0.02 = 100
        assertEquals(100.0, tax, 0.001);
    }

    @Test
    @DisplayName("Test Singapore Tax Calculation - Higher Bracket")
    void testSingaporeTaxHigherBracket() {
        TaxCalculator calculator = TaxServiceFactory.getService(Country.SINGAPORE, "Jane Doe");
        TaxRequest request = new TaxRequest(35000, 0);
        double tax = calculator.calculate(request);
        // Bracket: 200 + (35000 - 30000) * 0.035 = 200 + 175 = 375
        assertEquals(375.0, tax, 0.001);
    }

    @ParameterizedTest
    @CsvSource({
        "50000000, 0, 2500000",   // income <= 60jt (50jt * 0.05)
        "70000000, 0, 4500000"    // income > 60jt (60jt * 0.05 + 10jt * 0.15)
    })
    @DisplayName("Test Indonesia Tax Calculation")
    void testIndonesiaTax(double income, double expense, double expectedTax) {
        TaxCalculator calculator = TaxServiceFactory.getService(Country.INDONESIA, "Budi");
        TaxRequest request = new TaxRequest(income, expense);
        double tax = calculator.calculate(request);
        assertEquals(expectedTax, tax, 0.001);
    }

    @Test
    @DisplayName("Test Indonesia Tax Calculation with Expenses")
    void testIndonesiaTaxWithExpenses() {
        TaxCalculator calculator = TaxServiceFactory.getService(Country.INDONESIA, "Ali");
        // Net income = 100jt - 20jt = 80jt
        // Tax = (60jt * 0.05) + (20jt * 0.15) = 3jt + 3jt = 6jt
        TaxRequest request = new TaxRequest(100000000, 20000000);
        double tax = calculator.calculate(request);
        assertEquals(6000000.0, tax, 0.001);
    }
}
