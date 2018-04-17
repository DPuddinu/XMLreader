package com.example.dario.xmlreader;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private XmlParser xmlParser = new XmlParser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        xmlParser.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");
        CurrenciesList currenciesList = new CurrenciesList(mResponse);

        UIcontrol uIcontrol = new UIcontrol(this);
        uIcontrol.setupUI();
        uIcontrol.fetchData(currenciesList);

    }
}
