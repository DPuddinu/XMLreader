package com.example.dario.xmlreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class CurrencyDB extends Observable{

    private static CurrencyDB mInstance;
    private String lastUpdate;
    private List<CurrencyModel> currencyList = new ArrayList<>();
    private HashMap<String,Double> currencyMap = new HashMap<>();

    public CurrencyModel getLastItem(){
        return currencyList.get(currencyList.size()-1);
    }

    public static synchronized CurrencyDB getInstance() {
        if (mInstance == null) {
            mInstance = new CurrencyDB();
        }

        return mInstance;
    }
    public String getShortName(String fullname){
        for (int i = 0; i < currencyList.size(); i++) {
            if(fullname.equals(currencyList.get(i).getFullName())){
                return currencyList.get(i).getShortName();
            }
        }
        return null;
    }

    public void addCurrency(CurrencyModel currency){
        currencyList.add(currency);
        currencyMap.put(currency.getFullName(),currency.getRate());
        setChanged();
        notifyObservers();
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<CurrencyModel> getCurrencyList() {
        return currencyList;
    }

    public HashMap<String, Double> getCurrencyMap() {
        return currencyMap;
    }


    public Double getCurrencyValue(String name){

        return currencyMap.get(name);
    }
}
