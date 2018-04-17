package com.example.dario.xmlreader;

import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        model.setEnter((Button)activity.findViewById(R.id.invio));
        model.setFrom((Button)activity.findViewById(R.id.fromButton));
        model.setTo((Button)activity.findViewById(R.id.toButton));
        model.setAmount((EditText)activity.findViewById(R.id.etQuantita));
        model.setResults((TextView)activity.findViewById(R.id.tvRisultato));
        model.setTextViewFrom((TextView)activity.findViewById(R.id.from));
        model.setTextViewTo((TextView)activity.findViewById(R.id.to));
        model.setMenu1(new PopupMenu(model.getFrom().getContext(),model.getFrom()));
        model.setMenu2(new PopupMenu(model.getTo().getContext(),model.getTo()));
    }

    public void fetchData(final CurrenciesList currenciesList) {

        fillMenus(currenciesList,model.getMenu1(),model.getMenu2());


        model.getMenu1().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                model.getTextViewFrom().setText(item.getTitle().toString());
                CurrencyCalculator.getInstance().setFrom(currenciesList.getCurrencyValue(item.getTitle().toString()));


                return false;
            }
        });
        model.getMenu2().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                model.getTextViewTo().setText(item.getTitle().toString());
                CurrencyCalculator.getInstance().setTo(currenciesList.getCurrencyValue(item.getTitle().toString()));

                return false;
            }
        });

    }

    public void setupListeners(){
        model.getEnter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CurrencyCalculator.getInstance().setQuantity(Double.valueOf(model.getAmount().getText().toString()));
                if(CurrencyCalculator.getInstance().isReady()){
                    DecimalFormat df = new DecimalFormat("#.##");
                    model.getResults().setText(df.format(CurrencyCalculator.getInstance().calculate()));
                }
                else {
                    Log.e("errore ","inserire tutti i dati");
                }
            }
        });


        model.getFrom().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(model.getMenu1());
            }
        });

        model.getTo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showMenu(model.getMenu2());
            }
        });
    }

    private void fillMenus(CurrenciesList list, PopupMenu... menu){
        for (PopupMenu temp:menu
                ) {
            for (int i = 0; i < list.getCurrencyList().size(); i++) {
                temp.getMenu().add(list.getCurrencyList().get(i).getName());
            }
        }
    }
    private void showMenu(PopupMenu menu){
        menu.show();
    }

    public UImodel getModel() {
        return model;
    }

}
