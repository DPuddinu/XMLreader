package com.example.dario.xmlreader;

import android.util.Log;

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

    public void addTo(CurrencyModel currencyModel, ArrayList<CurrencyModel> modelArrayList, HashMap<String,Double> map){
        modelArrayList.add(currencyModel);
        map.put(currencyModel.getFullName(),currencyModel.getRate());
    }


    public void addcryptoCurrency(CurrencyModel currencyModel){
        cryptoCurrencyList.add(currencyModel);
        cryptoCurrencyMap.put(currencyModel.getFullName(),currencyModel.getRate());
        cryptoCurrencyNames.add(currencyModel.getFullName());
        setChanged();
        notifyObservers();
    }

    public void addCurrency(CurrencyModel currency){
        currencyList.add(currency);
        currencyNames.add(currency.getFullName());
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

    public String getShortName(String fullname,List<CurrencyModel> currencyList){


        String s="";
        for (int i = 0; i < currencyList.size(); i++) {
            if(currencyList.get(i).getFullName().equals(fullname)){
                s=currencyList.get(i).getId();
            }
        }

        return s;
    }

    public List<CurrencyModel> getCryptoCurrencyList() {
        return cryptoCurrencyList;
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
