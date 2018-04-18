package com.example.dario.xmlreader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyDB {

    private static CurrencyDB mInstance;
    private String lastUpdate;
    private List<CurrencyModel> currencyList = new ArrayList<>();
    private HashMap<String,Double> currencyMap = new HashMap<>();

    public static synchronized CurrencyDB getInstance() {
        if (mInstance == null) {
            mInstance = new CurrencyDB();
        }
        return mInstance;
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
