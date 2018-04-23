package com.example.dario.xmlreader;

public class CurrencyModel {
    private String fullName;
    private String id;
    private String rate;

    public CurrencyModel(String fullName, String id, String rate) {
        this.fullName=fullName;
        this.id = id;
        this.rate = rate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public Double getRate() {
        return Double.valueOf(rate);
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
