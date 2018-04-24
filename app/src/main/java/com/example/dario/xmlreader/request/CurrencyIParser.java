package com.example.dario.xmlreader.request;

import android.app.Activity;
import android.content.res.Resources;
import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.CurrencyModel;
import com.example.dario.xmlreader.R;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyIParser implements IParser {

    private Activity activity;

    public CurrencyIParser(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void parseDocument(String response ){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        Resources res = activity.getResources();
        String[] fullnames = res.getStringArray(R.array.fullnames);

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response)));

            NodeList currencies = document.getElementsByTagName("Cube");
            NamedNodeMap nodeMap = currencies.item(1).getAttributes();

            CurrencyDB.getInstance().setLastUpdate( nodeMap.getNamedItem("time").getNodeValue());

            CurrencyModel euro = new CurrencyModel("Euro","EUR","1");
            CurrencyDB.getInstance().addCurrency(euro);
            for (int i = 2; i < currencies.getLength() ; i++) {
                nodeMap = currencies.item(i).getAttributes();
                String shortName = nodeMap.getNamedItem("currency").getNodeValue();
                String value = nodeMap.getNamedItem("rate").getNodeValue();
                String fullName = fullnames[i-2];


                CurrencyModel currency = new CurrencyModel(fullName,shortName,value);
                CurrencyDB.getInstance().addCurrency(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
