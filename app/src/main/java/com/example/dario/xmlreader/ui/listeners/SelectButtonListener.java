package com.example.dario.xmlreader.ui.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.dario.xmlreader.ChangesManager;
import com.example.dario.xmlreader.ICalculate;
import com.example.dario.xmlreader.Operation;
import com.example.dario.xmlreader.ui.UIcontrol;

import java.text.DecimalFormat;

public class SelectButtonListener {
    private UIcontrol uicontrol;

    private DecimalFormat df = new DecimalFormat("#.####");
    private Activity activity;
    private ICalculate iCalculate;

    public SelectButtonListener(UIcontrol uicontrol, Activity activity) {
        this.uicontrol = uicontrol;
        this.activity=activity;
    }

    public void setupListeners() {

        uicontrol.getModel().getEnter().setOnClickListener(v -> {

            if(!uicontrol.getModel().isAmountEmpty())setAmount();

            if(ChangesManager.getInstance().getOperation().isReady())
            {
                uicontrol.getModel().setResults(results());
                ChangesManager.getInstance().addToHistory();
            }

            else Toast.makeText(activity,"Please insert all data",Toast.LENGTH_LONG).show();
        });

        uicontrol.getModel().getFrom().setOnClickListener(v -> {
            uicontrol.showRecyclerView(uicontrol.getModel().getRecyclerView1());
            if(uicontrol.getModel().getRecyclerView().getVisibility()==View.VISIBLE)uicontrol.getModel().getRecyclerView().setVisibility(View.INVISIBLE);

        });

        uicontrol.getModel().getTo().setOnClickListener(v ->{
            uicontrol.showRecyclerView(uicontrol.getModel().getRecyclerView());
            if(uicontrol.getModel().getRecyclerView1().getVisibility()==View.VISIBLE)uicontrol.getModel().getRecyclerView1().setVisibility(View.INVISIBLE);

        });
    }

    public void setiCalculate(ICalculate iCalculate) {
        this.iCalculate = iCalculate;
    }

    private String results(){
        Operation operation = ChangesManager.getInstance().getOperation();

        Double from = operation.getFrom();
        Double to = operation.getTo();
        Double amount = operation.getAmount();

        return df.format(iCalculate.calculate(from,to,amount));
    }

    private void setAmount() {
        Double amount = Double.valueOf(uicontrol.getModel().getAmount().getText().toString());
        ChangesManager.getInstance().getOperation().setAmount(amount);
    }
}
