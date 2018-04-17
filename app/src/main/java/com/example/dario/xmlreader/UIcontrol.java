package com.example.dario.xmlreader;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class UIcontrol {

    private UImodel model = new UImodel();
    private Activity activity;
    public UIcontrol(Activity activity) {
        this.activity=activity;
    }

    public void setupUI(){
        model.setEnter((activity.findViewById(R.id.invio)));
        model.setFrom(activity.findViewById(R.id.fromButton));
        model.setTo(activity.findViewById(R.id.toButton));
        model.setAmount(activity.findViewById(R.id.etQuantita));
        model.setResults(activity.findViewById(R.id.tvRisultato));
        model.setTextViewFrom(activity.findViewById(R.id.from));
        model.setTextViewTo(activity.findViewById(R.id.to));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));
        model.setMenu1(new PopupMenu(model.getFrom().getContext(),model.getFrom()));
        model.setMenu2(new PopupMenu(model.getTo().getContext(),model.getTo()));

    }

    //DA NOTARE L'UTILIZZO DELLE LAMBDA EXPRESSION ANZICHE' L'USO DI CLASSI ANONIME
    public void setupListeners(final CurrencyControl currencyControl) {

        model.getLastUpdate().setText("Last Update: "+ currencyControl.getLastUpdate());
        fillMenus(currencyControl,model.getMenu1(),model.getMenu2());

        model.getMenu1().setOnMenuItemClickListener(item -> {
            setTextViewCurrencyName(model.getTextViewFrom(),item);
            CurrencyCalculator.getInstance().setFrom(currencyControl.getCurrencyValue(item.getTitle().toString()));
            return false;
        });
        model.getMenu2().setOnMenuItemClickListener(item -> {
            setTextViewCurrencyName(model.getTextViewTo(),item);
            CurrencyCalculator.getInstance().setTo(currencyControl.getCurrencyValue(item.getTitle().toString()));
            return false;
        });
        model.getEnter().setOnClickListener(v -> {

            if(!model.isAmountEmpty())setAmount();

            if(CurrencyCalculator.getInstance().isReady()){
                DecimalFormat df = new DecimalFormat("#.##");
                model.getResults().setText(df.format(CurrencyCalculator.getInstance().calculate()));
            }
            else Toast.makeText(activity,"Inserire tutti i dati",Toast.LENGTH_LONG).show();
        });

        model.getFrom().setOnClickListener(v -> model.getMenu1().show());

        model.getTo().setOnClickListener(v -> model.getMenu2().show());
    }

    private void setAmount() {
        CurrencyCalculator.getInstance().setQuantity(Double.valueOf(model.getAmount().getText().toString()));
    }

    private void fillMenus(CurrencyControl list, PopupMenu... menu){
        for (PopupMenu temp:menu
                ) {
            for (CurrencyModel currency: list.getCurrencyList()
                 ) {
                temp.getMenu().add(currency.getName());
            }
        }
    }
    private void setTextViewCurrencyName(TextView currencyName, MenuItem item){
        currencyName.setText(item.getTitle().toString());
    }
}
