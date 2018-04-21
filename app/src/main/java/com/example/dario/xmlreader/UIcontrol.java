package com.example.dario.xmlreader;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class UIcontrol implements Observer{
    private CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private UImodel model = new UImodel();
    private Activity activity;
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private boolean isFrom=true;
    private ArrayAdapter<String> adapter1;

    public UIcontrol(Activity activity) {
        this.activity=activity;
    }
    public void setupListeners() {

        model.getList1().setOnItemClickListener((parent, view, position, id) -> {
            sourceSetup(parent.getItemAtPosition(position).toString());
            showListView();
        });

        model.getEnter().setOnClickListener(v -> {

            if(!model.isAmountEmpty())setAmount();

            if(currencyCalculator.isReady()){
                DecimalFormat df = new DecimalFormat("#.##");
                model.getResults().setText(df.format(currencyCalculator.calculate()));
            }
            else Toast.makeText(activity,"Please insert all data",Toast.LENGTH_LONG).show();
        });

        model.getFrom().setOnClickListener(v -> {
            showListView();
            isFrom=true;
        });

        model.getTo().setOnClickListener(v ->{
           showListView();
           isFrom=false;
        });
    }
    private void sourceSetup(String name){

        if(isFrom){
            currencyCalculator.setFrom(CurrencyDB.getInstance().getCurrencyValue(name));
            model.getTextViewFrom().setText(CurrencyDB.getInstance().getShortName(name));
        }
        else {
            model.getTextViewTo().setText(CurrencyDB.getInstance().getShortName(name));
            currencyCalculator.setTo(CurrencyDB.getInstance().getCurrencyValue(name));
        }
    }
    public void hideListView(){
        model.getList1().setVisibility(View.INVISIBLE);
    }

    public void showListView(){
        if(model.getList1().getVisibility()==View.VISIBLE)model.getList1().setVisibility(View.INVISIBLE);
        else model.getList1().setVisibility(View.VISIBLE);

    }
    public void setupDate(){
        model.getLastUpdate().setText(String.valueOf("Last update: " + CurrencyDB.getInstance().getLastUpdate()));
    }
    public void setupAdapter(){
        adapter1 =new ArrayAdapter<>(activity,R.layout.row,arrayList1);
        model.getList1().setAdapter(adapter1);
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
        model.setList1(activity.findViewById(R.id.listview1));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));

    }
    private void setAmount() {
        currencyCalculator.setQuantity(Double.valueOf(model.getAmount().getText().toString()));
    }

    @Override
    public void update(Observable o, Object arg) {
        String lastItem = CurrencyDB.getInstance().getLastItem().getFullName();
        arrayList1.add(lastItem);
        updateList();
    }
    public void updateList(){
        adapter1.notifyDataSetChanged();

    }
}
