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

        View decorView = getWindow().getDecorView();

        int uiOptions =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility(uiOptions);

        xmlParser.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");
        CurrenciesList currenciesList = new CurrenciesList(mResponse);


        UIcontrol uIcontrol = new UIcontrol(this);
        uIcontrol.setupUI();
        uIcontrol.setupListeners();
        uIcontrol.fetchData(currenciesList);

    }
}
