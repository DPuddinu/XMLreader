package com.example.dario.xmlreader.ui;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.R;
import com.example.dario.xmlreader.request.CurrencyCalculator;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class UIcontrol implements Observer{

    private CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private UImodel model = new UImodel();
    private Activity activity;
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private boolean isFrom=true;

    private RecycleViewAdapter rowAdapter;

    public UIcontrol(Activity activity) {
        this.activity=activity;
    }

    @Override
    public void update(Observable o, Object arg) {
        String lastItem = CurrencyDB.getInstance().getLastItem().getFullName();
        arrayList1.add(lastItem);
        rowAdapter.notifyItemRangeChanged(0,arrayList1.size());
    }
    //VA INVOCATO QUANDO SI SCEGLIE UNA VALUTA
    public void sourceSetup(String name){

        if(isFrom){
            currencyCalculator.setFrom(CurrencyDB.getInstance().getCurrencyValue(name));
            model.getTextViewFrom().setText(CurrencyDB.getInstance().getShortName(name));
        }
        else {
            currencyCalculator.setTo(CurrencyDB.getInstance().getCurrencyValue(name));
            model.getTextViewTo().setText(CurrencyDB.getInstance().getShortName(name));
        }
    }

    public void setupDate(){
        model.getLastUpdate().setText(String.valueOf("Last update: " + CurrencyDB.getInstance().getLastUpdate()));
    }
    public void setupAdapter(){
        rowAdapter = new RecycleViewAdapter(this,activity,arrayList1);
        model.getmRecyclerView().setAdapter(rowAdapter);
    }

    public void setupViews(){

        model.setEnter((activity.findViewById(R.id.invio)));
        model.setFrom(activity.findViewById(R.id.fromButton));
        model.setTo(activity.findViewById(R.id.toButton));
        model.setAmount(activity.findViewById(R.id.etQuantita));
        model.setTextViewResults(activity.findViewById(R.id.tvRisultato));
        model.setTextViewFrom(activity.findViewById(R.id.from));
        model.setTextViewTo(activity.findViewById(R.id.to));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));
        model.setmRecyclerView(activity.findViewById(R.id.my_recycler_view));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));
        model.setmRecyclerView(activity.findViewById(R.id.my_recycler_view));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        model.getmRecyclerView().setLayoutManager(linearLayoutManager);
    }

    public void setFrom(boolean from) {
        isFrom = from;
    }
    public boolean isFrom() {
        return isFrom;
    }

    public CurrencyCalculator getCurrencyCalculator() {
        return currencyCalculator;
    }

    public UImodel getModel() {
        return model;
    }
}
