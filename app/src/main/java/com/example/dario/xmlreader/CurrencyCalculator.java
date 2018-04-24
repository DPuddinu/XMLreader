package com.example.dario.xmlreader;

public class CurrencyCalculator {


    private double from;
    private double to;
    private double quantity;


    public boolean isReady(){
        return from != 0 && to != 0 && quantity != 0;
    }

    public double calculate(){
        return (quantity*from)/to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
