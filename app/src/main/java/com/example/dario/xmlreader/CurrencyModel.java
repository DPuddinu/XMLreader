package com.example.dario.xmlreader;

public class CurrencyModel {
    private String fullName;
    private String shortName;
    private String rate;

    public CurrencyModel(String shortName, String rate) {
        this.shortName = shortName;
        this.rate = rate;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Double getRate() {
        return Double.valueOf(rate);
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
