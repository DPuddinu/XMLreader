package com.example.dario.xmlreader;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.PopupMenu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private XmlParser xmlParser = new XmlParser(this);
    private ChangesCalculator changesCalculator = new ChangesCalculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xmlParser.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");
        CurrencyControl currencyControl = new CurrencyControl();
        currencyControl.parseDocument(mResponse);

        Log.e("size ",""+currencyControl.getCurrencyList().size());
        UIcontrol uIcontrol = new UIcontrol(this,changesCalculator);
        uIcontrol.setupUI();
        uIcontrol.setupListeners();
        PopupMenuControl popupMenuControl = new PopupMenuControl(currencyControl);
        popupMenuControl.fetchData(uIcontrol,changesCalculator);

    }
}
