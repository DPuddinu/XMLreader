package com.example.dario.xmlreader;

public class CurrencyModel {
    private String name;
    private String rate;

    public CurrencyModel(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return Double.valueOf(rate);
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}