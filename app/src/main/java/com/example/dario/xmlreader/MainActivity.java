package com.example.dario.xmlreader;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private RequestHandler requestHandler = new RequestHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        requestHandler.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");

        ResponseParser responseParser = new ResponseParser();
        responseParser.parseDocument(mResponse,CurrencyDB.getInstance().getCurrencyList(),CurrencyDB.getInstance().getCurrencyMap());

        UIcontrol uIcontrol = new UIcontrol(this);
        uIcontrol.setupUI();
        uIcontrol.setupListeners();
    }
}
