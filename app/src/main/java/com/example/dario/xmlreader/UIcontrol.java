package com.example.dario.xmlreader;
import android.app.Activity;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;


public class UIcontrol implements Observer{
    private CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private UImodel model = new UImodel();
    private Activity activity;

    public UIcontrol(Activity activity) {
        this.activity=activity;
    }

    public void setupListeners() {

        model.getMenu1().setOnMenuItemClickListener(item -> {
            model.getTextViewFrom().setText(CurrencyDB.getInstance().getShortName(item.getTitle().toString()));
            currencyCalculator.setFrom(CurrencyDB.getInstance().getCurrencyValue(item.getTitle().toString()));
            return false;
        });
        model.getMenu2().setOnMenuItemClickListener(item -> {
            model.getTextViewTo().setText(CurrencyDB.getInstance().getShortName(item.getTitle().toString()));
            currencyCalculator.setTo(CurrencyDB.getInstance().getCurrencyValue(item.getTitle().toString()));
            return false;
        });
        model.getEnter().setOnClickListener(v -> {

            if(!model.isAmountEmpty())setAmount();

            if(currencyCalculator.isReady()){
                DecimalFormat df = new DecimalFormat("#.##");
                model.getResults().setText(df.format(currencyCalculator.calculate()));
            }
            else Toast.makeText(activity,"Inserire tutti i dati",Toast.LENGTH_LONG).show();
        });

        model.getFrom().setOnClickListener(v -> model.getMenu1().show());
        model.getTo().setOnClickListener(v -> model.getMenu2().show());
    }
    public void setupDate(){
        model.getLastUpdate().setText(String.valueOf("Last update: " + CurrencyDB.getInstance().getLastUpdate()));
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
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));

    }
    private void setAmount() {
        currencyCalculator.setQuantity(Double.valueOf(model.getAmount().getText().toString()));
    }


    @Override
    public void update(Observable o, Object arg) {
        String lastItem = CurrencyDB.getInstance().getLastItem().getFullName();
        model.getMenu1().getMenu().add(lastItem);
        model.getMenu2().getMenu().add(lastItem);
    }
}
