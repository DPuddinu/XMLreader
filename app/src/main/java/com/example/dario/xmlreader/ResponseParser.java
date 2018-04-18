package com.example.dario.xmlreader;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ResponseParser {


    public void parseDocument(String response ){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response)));

            NodeList currencies = document.getElementsByTagName("Cube");
            NamedNodeMap nodeMap = currencies.item(1).getAttributes();

            CurrencyDB.getInstance().setLastUpdate( nodeMap.getNamedItem("time").getNodeValue());
            Log.e("time", CurrencyDB.getInstance().getLastUpdate());
            CurrencyModel euro = new CurrencyModel("EUR","1");
            CurrencyDB.getInstance().addCurrency(euro);
            for (int i = 2; i < currencies.getLength() ; i++) {
                nodeMap = currencies.item(i).getAttributes();
                String name = nodeMap.getNamedItem("currency").getNodeValue();
                String value = nodeMap.getNamedItem("rate").getNodeValue();

                CurrencyModel currency = new CurrencyModel(name,value);
                CurrencyDB.getInstance().addCurrency(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
