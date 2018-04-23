package com.example.dario.xmlreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class CurrencyDB extends Observable{

    private static CurrencyDB mInstance;
    private String lastUpdate;
    private List<CurrencyModel> cryptoCurrencyList = new ArrayList<>();
    private List<CurrencyModel> currencyList = new ArrayList<>();
    private HashMap<String,Double> cryptoCurrencyMap = new HashMap<>();
    private HashMap<String,Double> currencyMap = new HashMap<>();
    private ArrayList<String> currencyNames = new ArrayList<>();
    private ArrayList<String> cryptoCurrencyNames = new ArrayList<>();

    public ArrayList<String> getCurrencyNames() {
        return currencyNames;
    }

    public ArrayList<String> getCryptoCurrencyNames() {
        return cryptoCurrencyNames;
    }

    public static synchronized CurrencyDB getInstance() {
        if (mInstance == null) {
            mInstance = new CurrencyDB();
        }
        return mInstance;
    }

    public void addcryptoCurrency(CurrencyModel currencyModel){
        cryptoCurrencyList.add(currencyModel);
        cryptoCurrencyMap.put(currencyModel.getFullName(),currencyModel.getRate());
        setChanged();
        notifyObservers();
    }

    public void addCurrency(CurrencyModel currency){
        currencyList.add(currency);
        currencyMap.put(currency.getFullName(),currency.getRate());
        setChanged();
        notifyObservers();
    }
    public void loadNames(){
        for (CurrencyModel model:currencyList
             ) {
            currencyNames.add(model.getFullName());
        }
        for (CurrencyModel model:cryptoCurrencyList
             ) {
            cryptoCurrencyNames.add(model.getFullName());
        }
    }

    public String getShortName(String fullname){
        for (int i = 0; i < currencyList.size(); i++) {
            if(fullname.equals(currencyList.get(i).getFullName())){
                return currencyList.get(i).getId();
            }
        }
        for (int i = 0; i < cryptoCurrencyList.size(); i++) {
            if(fullname.equals(cryptoCurrencyList.get(i).getFullName())){
                return cryptoCurrencyList.get(i).getId();
            }
        }

        return null;
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

    public Double getCurrencyValue(String name){
        return currencyMap.get(name);
    }
    public Double getCryptoCurrencyValue(String name){
        return cryptoCurrencyMap.get(name);
    }
}
