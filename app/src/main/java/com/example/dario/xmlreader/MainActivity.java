package com.example.dario.xmlreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private RequestHandler requestHandler = new RequestHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        requestHandler.fetchDocument();
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");

        ResponseParser responseParser = new ResponseParser();
        UIcontrol uIcontrol = new UIcontrol(this);
        CurrencyDB.getInstance().addObserver(uIcontrol);
        uIcontrol.setupUI();
        responseParser.parseDocument(this,mResponse);
        uIcontrol.setupDate();
        loadFullNames();
        uIcontrol.setupListeners();
    }

    private void loadFullNames(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // TODO
                return true;

            case R.id.action_favorite:
                // TODO
                return true;

            case R.id.add_currency:
                Intent intent = new Intent(this, CustomCurrency.class);
                startActivity(intent);
            default:

            return super.onOptionsItemSelected(item);
        }
    }
}
