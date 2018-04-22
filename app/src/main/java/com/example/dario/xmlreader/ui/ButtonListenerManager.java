package com.example.dario.xmlreader.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.dario.xmlreader.CurrencyDB;

import java.text.DecimalFormat;

public class ButtonListenerManager {
    private UIcontrol uicontrol;

    private DecimalFormat df = new DecimalFormat("#.####");
    private Activity activity;
    public ButtonListenerManager(UIcontrol uicontrol, Activity activity) {
        this.uicontrol = uicontrol;
        this.activity=activity;

    }


    public void setupListeners() {

        uicontrol.getModel().getEnter().setOnClickListener(v -> {

            if(!uicontrol.getModel().isAmountEmpty())setAmount();

            if(uicontrol.getCurrencyCalculator().isReady()) uicontrol.getModel().setResults(results());

            else Toast.makeText(activity,"Please insert all data",Toast.LENGTH_LONG).show();
        });

        uicontrol.getModel().getFrom().setOnClickListener(v -> {
            uicontrol.showRecyclerView();

            uicontrol.setFrom(true);
        });

        uicontrol.getModel().getTo().setOnClickListener(v ->{
            uicontrol.showRecyclerView();
            uicontrol.setFrom(false);
        });

    }
    private String results(){
        return df.format(uicontrol.getCurrencyCalculator().calculate());
    }

    private void setAmount() {
        uicontrol.getCurrencyCalculator().setQuantity(Double.valueOf(uicontrol.getModel().getAmount().getText().toString()));
    }



}
