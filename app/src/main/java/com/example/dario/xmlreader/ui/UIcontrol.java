package com.example.dario.xmlreader.ui;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.R;
import com.example.dario.xmlreader.CurrencyCalculator;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UIcontrol implements Observer{

    //TOGLIERE CurrencyCalculator da qua
    private CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private UImodel model = new UImodel();
    private Activity activity;
    //TOGLIERE RecyclerViewListener da qua
    private RecyclerViewListener recyclerViewListener = new RecyclerViewListener(this,activity);
    private ArrayList<String> currenciesList = CurrencyDB.getInstance().getCurrencyNames();
    private ArrayList<String> cryptoCurrenciesList = CurrencyDB.getInstance().getCryptoCurrencyNames();
    private RecycleViewAdapter currencyAdapter;
    private RecycleViewAdapter cryptoCurrencyAdapter;

    public UIcontrol(Activity activity) {
        this.activity=activity;
        setupViews();
        setupAdapter();
    }

    @Override
    public void update(Observable o, Object arg) {

        currencyAdapter.notifyDataSetChanged();
        cryptoCurrencyAdapter.notifyDataSetChanged();
    }

    public void setupAdapter(){
        currencyAdapter = new RecycleViewAdapter(activity, currenciesList);
        cryptoCurrencyAdapter = new RecycleViewAdapter(activity,cryptoCurrenciesList);
        model.getRecyclerView().setAdapter(currencyAdapter);
        model.getRecyclerView1().setAdapter(cryptoCurrencyAdapter);
        recyclerViewListener.setListener();
    }

    public void hideRecyclerView(RecyclerView recyclerView){
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void showRecyclerView(RecyclerView recyclerView){
        if(recyclerView.getVisibility()==View.INVISIBLE) recyclerView.setVisibility(View.VISIBLE);
        else recyclerView.setVisibility(View.INVISIBLE);
    }

    public void setupDate(){
        model.getLastUpdate().setText(String.valueOf("Last update: " + CurrencyDB.getInstance().getLastUpdate()));
    }

    public void setupViews() {

        model.setEnter((activity.findViewById(R.id.invio)));
        model.setFrom(activity.findViewById(R.id.fromButton));
        model.setTo(activity.findViewById(R.id.toButton));
        model.setAmount(activity.findViewById(R.id.etQuantita));
        model.setTextViewResults(activity.findViewById(R.id.tvRisultato));
        model.setTextViewFrom(activity.findViewById(R.id.from));
        model.setTextViewTo(activity.findViewById(R.id.to));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));
        model.setRecyclerView(activity.findViewById(R.id.recycler_currency));
        model.setRecyclerView1(activity.findViewById(R.id.recycler_cryptourrency));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity);
        model.getRecyclerView().setLayoutManager(linearLayoutManager);
        model.getRecyclerView1().setLayoutManager(linearLayoutManager1);
    }

    public RecycleViewAdapter getCryptoCurrencyAdapter() {
        return cryptoCurrencyAdapter;
    }

    public ArrayList<String> getCryptoCurrenciesList() {
        return cryptoCurrenciesList;
    }

    public ArrayList<String> getCurrenciesList() {
        return currenciesList;
    }

    public RecycleViewAdapter getCurrencyAdapter() {
        return currencyAdapter;
    }

    public CurrencyCalculator getCurrencyCalculator() {
        return currencyCalculator;
    }

    public UImodel getModel() {
        return model;
    }
}
