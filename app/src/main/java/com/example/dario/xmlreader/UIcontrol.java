package com.example.dario.xmlreader;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
    private RecycleViewAdapter rowAdapter;

    public UIcontrol(Activity activity) {
        this.activity=activity;
    }
    public void setupListeners() {

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

        model.getmRecyclerView().setOnLongClickListener(v -> {
//                itemPosition=rowAdapter.


            return false;
        });

    }

    public void sourceSetup(String name){

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
        model.getmRecyclerView().setVisibility(View.INVISIBLE);
    }

    public void showListView(){
        if(model.getmRecyclerView().getVisibility()==View.VISIBLE)model.getmRecyclerView().setVisibility(View.INVISIBLE);
        else model.getmRecyclerView().setVisibility(View.VISIBLE);
    }
    public void setupContextMenu(){
        activity.registerForContextMenu(model.getmRecyclerView());
    }
    public void setupDate(){
        model.getLastUpdate().setText(String.valueOf("Last update: " + CurrencyDB.getInstance().getLastUpdate()));
    }
    public void setupAdapter(){
        rowAdapter = new RecycleViewAdapter(this,activity,arrayList1);
        model.getmRecyclerView().setAdapter(rowAdapter);

    }

    public Activity getActivity() {
        return activity;
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
        model.setmRecyclerView(activity.findViewById(R.id.my_recycler_view));
        model.setLastUpdate(activity.findViewById(R.id.lastUpdate));
        model.setmRecyclerView(activity.findViewById(R.id.my_recycler_view));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        model.getmRecyclerView().setLayoutManager(linearLayoutManager);
    }
    private void setAmount() {
        currencyCalculator.setQuantity(Double.valueOf(model.getAmount().getText().toString()));
    }

    @Override
    public void update(Observable o, Object arg) {
        String lastItem = CurrencyDB.getInstance().getLastItem().getFullName();
        arrayList1.add(lastItem);
    }

    public boolean popupOperation(int itemId) {
        switch (itemId){
            case R.id.remove:
                arrayList1.remove(rowAdapter.getItemPosition());
                rowAdapter.notifyDataSetChanged();
                return true;


            default: return false;
        }
    }
}
