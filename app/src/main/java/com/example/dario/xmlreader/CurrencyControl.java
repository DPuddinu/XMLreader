package com.example.dario.xmlreader;

import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyControl {

    private List<Currency> currencyList = new ArrayList<>();

    public void parseDocument(String string){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(string)));

            NodeList currencies = document.getElementsByTagName("Cube");

            for (int i = 2; i < currencies.getLength() ; i++) {
                NamedNodeMap nodeMap = currencies.item(i).getAttributes();
                String name = nodeMap.getNamedItem("currency").getNodeValue();
                String value = nodeMap.getNamedItem("rate").getNodeValue();
                Log.e(name,value);
                Currency currency = new Currency(name,value);
                currencyList.add(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double getCurrencyValue(String name){

        for (int i = 0; i < currencyList.size() ; i++) {
            if(name.equals(currencyList.get(i).getName())){
              return currencyList.get(i).getRate();
            }
        }
        return null;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
