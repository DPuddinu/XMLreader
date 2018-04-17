package com.example.dario.xmlreader;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrenciesList {

    private List<CurrencyModel> currencyList = new ArrayList<>();
    private HashMap<String,Double> currencyMap = new HashMap<>();
    private String response;

    public CurrenciesList(String response) {
        this.response = response;
        parseDocument();
    }

    public void parseDocument(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response)));

            NodeList currencies = document.getElementsByTagName("Cube");

            for (int i = 2; i < currencies.getLength() ; i++) {
                NamedNodeMap nodeMap = currencies.item(i).getAttributes();
                String name = nodeMap.getNamedItem("currency").getNodeValue();
                String value = nodeMap.getNamedItem("rate").getNodeValue();

                CurrencyModel currency = new CurrencyModel(name,value);
                currencyList.add(currency);
                currencyMap.put(name,Double.valueOf(value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double getCurrencyValue(String name){

        return currencyMap.get(name);
    }

    public List<CurrencyModel> getCurrencyList() {
        return currencyList;
    }
}
