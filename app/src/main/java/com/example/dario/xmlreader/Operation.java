package com.example.dario.xmlreader;

public class Operation {

    private String nameFrom;
    private String nameTo;
    private double from;
    private double to;
    private double amount;

    public boolean isReady(){
        return nameFrom!=null && nameTo!=null && from!=0 && to!=0 && amount!=0;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
