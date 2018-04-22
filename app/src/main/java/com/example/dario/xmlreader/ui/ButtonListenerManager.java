package com.example.dario.xmlreader.ui;

import android.app.Activity;
import android.widget.Toast;

import com.example.dario.xmlreader.CurrencyDB;

import java.text.DecimalFormat;

public class ButtonListenerManager {
    private UIcontrol uicontrol;
    private RecyclerViewManager recyclerViewManager;
    private DecimalFormat df = new DecimalFormat("#.####");
    private Activity activity;
    public ButtonListenerManager(UIcontrol uicontrol, RecyclerViewManager recyclerViewManager, Activity activity) {
        this.uicontrol = uicontrol;
        this.activity=activity;
        this.recyclerViewManager=recyclerViewManager;
    }


    public void setupListeners() {

        uicontrol.getModel().getEnter().setOnClickListener(v -> {

            if(!uicontrol.getModel().isAmountEmpty())setAmount();

            if(uicontrol.getCurrencyCalculator().isReady()) uicontrol.getModel().setResults(results());

            else Toast.makeText(activity,"Please insert all data",Toast.LENGTH_LONG).show();
        });

        uicontrol.getModel().getFrom().setOnClickListener(v -> {
            recyclerViewManager.showRecyclerView();
            uicontrol.setFrom(true);
        });

        uicontrol.getModel().getTo().setOnClickListener(v ->{
            recyclerViewManager.showRecyclerView();
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
