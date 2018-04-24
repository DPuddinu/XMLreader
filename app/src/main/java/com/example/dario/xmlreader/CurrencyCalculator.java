package com.example.dario.xmlreader;

public class CurrencyCalculator implements ICalculate {

    @Override
    public double calculate(Double from, Double to, Double amount) {
        return (amount*from)*to;
    }
}
