package com.example.dario.xmlreader;

public class CurrencyModel {
    private String fullName;
    private String shortName;
    private String rate;

    public CurrencyModel(String fullName,String shortName, String rate) {
        this.fullName=fullName;
        this.shortName = shortName;
        this.rate = rate;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Double getRate() {
        return Double.valueOf(rate);
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
