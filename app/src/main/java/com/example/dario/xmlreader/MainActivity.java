package com.example.dario.xmlreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.dario.xmlreader.request.CryptoCurrencyIParser;
import com.example.dario.xmlreader.request.CryptoCurrencyIRequest;
import com.example.dario.xmlreader.request.CurrencyIRequest;
import com.example.dario.xmlreader.ui.listeners.SelectButtonListener;
import com.example.dario.xmlreader.request.RequestHandler;
import com.example.dario.xmlreader.request.CurrencyIParser;
import com.example.dario.xmlreader.ui.listeners.CryptocurrencyItemListener;
import com.example.dario.xmlreader.ui.listeners.CurrencyItemListener;
import com.example.dario.xmlreader.ui.UIcontrol;


public class MainActivity extends AppCompatActivity {

    private RequestHandler requestHandler = new RequestHandler(this);
    private UIcontrol uIcontrol;
    private SelectButtonListener selectButtonListener;
    private CurrencyIParser currencyParser;
    private CryptoCurrencyIParser cryptoCurrencyParser;
    private CurrencyCalculator currencyCalculator = new CurrencyCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //FACCIO LE RICHIESTE
        requestHandler.doRequest(new CurrencyIRequest(),"currency");
        requestHandler.doRequest(new CryptoCurrencyIRequest(),"cryptocurrency");

        //SALVO I RESPONSE NELLE STRINGHE SEGUENTI
        String currencyResponse = sharedPreferences.getString("currency", "");
        String cryptoCurrencyResponse = sharedPreferences.getString("cryptocurrency", "");

        //CREO I PARSER
        currencyParser = new CurrencyIParser(this);
        cryptoCurrencyParser = new CryptoCurrencyIParser();

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
        selectButtonListener = new SelectButtonListener(uIcontrol,this);
        selectButtonListener.setupListeners();
        selectButtonListener.setiCalculate(new CurrencyCalculator());

        //CREO I LISTENER CHE GESTISCONO I COMPORTAMENTI DELLE VALUTE QUANDO VENGONO SELEZIONATE
        CryptocurrencyItemListener cryptocurrencyItemListener = new CryptocurrencyItemListener(uIcontrol,this);
        cryptocurrencyItemListener.setListener();

        CurrencyItemListener CurrencyItemListener = new CurrencyItemListener(uIcontrol,this);
        CurrencyItemListener.setListener();

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
