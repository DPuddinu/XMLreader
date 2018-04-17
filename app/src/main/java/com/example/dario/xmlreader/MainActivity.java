package com.example.dario.xmlreader;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private XmlParser xmlParser = new XmlParser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        xmlParser.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");
        CurrencyControl currencyControl = new CurrencyControl(mResponse);

        UIcontrol uIcontrol = new UIcontrol(this);
        uIcontrol.setupUI();
        uIcontrol.setupListeners(currencyControl);

    }
}
