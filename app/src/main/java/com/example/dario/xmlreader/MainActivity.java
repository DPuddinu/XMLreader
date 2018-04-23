package com.example.dario.xmlreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.dario.xmlreader.request.CryptoCurrencyParser;
import com.example.dario.xmlreader.request.CryptoCurrencyRequest;
import com.example.dario.xmlreader.request.CurrencyRequest;
import com.example.dario.xmlreader.ui.ButtonListenerManager;
import com.example.dario.xmlreader.request.RequestHandler;
import com.example.dario.xmlreader.request.CurrencyParser;
import com.example.dario.xmlreader.ui.UIcontrol;


public class MainActivity extends AppCompatActivity {

    private RequestHandler requestHandler = new RequestHandler(this);
    private UIcontrol uIcontrol;
    private ButtonListenerManager buttonListenerManager;
    private CurrencyParser currencyParser;
    private CryptoCurrencyParser cryptoCurrencyParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //FACCIO LE RICHIESTE
        requestHandler.doRequest(new CurrencyRequest(),"currency");
        requestHandler.doRequest(new CryptoCurrencyRequest(),"cryptocurrency");

        //SALVO I RESPONSE NELLE STRINGHE SEGUENTI
        String currencyResponse = sharedPreferences.getString("currency", "");
        String cryptoCurrencyResponse = sharedPreferences.getString("cryptocurrency", "");

        //CREO I PARSER
        currencyParser = new CurrencyParser(this);
        cryptoCurrencyParser = new CryptoCurrencyParser();

        //CREO UIcontrol E SETTO L'OBSERVER
        uIcontrol = new UIcontrol(this);
        CurrencyDB.getInstance().addObserver(uIcontrol);

        //ESEGUO I PARSE DEI DUE DOCUMENTI E CARICO I RISULTATI NEGLI ARRAYLIST CORRISPONDENTI DEL DB
        cryptoCurrencyParser.parseDocument(cryptoCurrencyResponse);
        currencyParser.parseDocument(currencyResponse);
        CurrencyDB.getInstance().loadNames();

        //VISUALIZZO LA DATA DELL'ULTIMO AGGIORNAMENTO DEI DATI
        uIcontrol.setupDate();

        //CREO LA CLASSE CHE SI OCCUPA DEI BUTTONLISTENER
        buttonListenerManager = new ButtonListenerManager(uIcontrol,this);
        buttonListenerManager.setupListeners();
        names();
    }

    public void names(){
        Log.e("currency size",""+CurrencyDB.getInstance().getCurrencyNames().size());
        Log.e("cryptoCurrency size",""+CurrencyDB.getInstance().getCryptoCurrencyNames().size());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        uIcontrol.hideRecyclerView(uIcontrol.getModel().getRecyclerView());
        uIcontrol.hideRecyclerView(uIcontrol.getModel().getRecyclerView1());
        return super.onTouchEvent(event);
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
