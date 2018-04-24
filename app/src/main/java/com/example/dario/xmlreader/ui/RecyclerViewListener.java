package com.example.dario.xmlreader.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dario.xmlreader.CurrencyDB;

public class RecyclerViewListener {
    private UIcontrol uicontrol;
    private Context context;

    public RecyclerViewListener(UIcontrol uicontrol, Context context) {
        this.uicontrol = uicontrol;
        this.context = context;
    }

    public void setListener(){

        //LISTENER TASTO FROM
        RecyclerTouchManager touchlistener1 = new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                if(!uicontrol.getCurrencyAdapter().isMultiSelect()){
                    String fullname=uicontrol.getCryptoCurrenciesList().get(position) ;
                    String id=CurrencyDB.getInstance().getShortName(fullname,CurrencyDB.getInstance().getCryptoCurrencyList());
                    Double value= CurrencyDB.getInstance().getCryptoCurrencyValue(fullname);

                    uicontrol.getCurrencyCalculator().setFrom(value);
                    uicontrol.getModel().getTextViewFrom().setText(id);
                    uicontrol.hideRecyclerView(uicontrol.getModel().getRecyclerView1());
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
            }
        });

        //LISTENER TASTO TO
        RecyclerTouchManager touchlistener = new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                if(!uicontrol.getCurrencyAdapter().isMultiSelect()){
                    String fullname=uicontrol.getCurrenciesList().get(position) ;
                    String id=CurrencyDB.getInstance().getShortName(fullname,CurrencyDB.getInstance().getCurrencyList());
                    Double value= CurrencyDB.getInstance().getCurrencyValue(fullname);

                    uicontrol.getCurrencyCalculator().setTo(value);
                    uicontrol.getModel().getTextViewTo().setText(id);

                    uicontrol.hideRecyclerView(uicontrol.getModel().getRecyclerView());
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
            }
        });

        uicontrol.getModel().getRecyclerView().addOnItemTouchListener(touchlistener);
        uicontrol.getModel().getRecyclerView1().addOnItemTouchListener(touchlistener1);
    }

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            uicontrol.getCurrencyAdapter().setMultiSelect(true);
            uicontrol.getCryptoCurrencyAdapter().setMultiSelect(true);
            menu.add("Delete");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            for (String string : uicontrol.getCurrencyAdapter().getSelectedElements()) {
                uicontrol.getCurrencyAdapter().getElements().remove(string);
            }
            for (String string : uicontrol.getCryptoCurrencyAdapter().getSelectedElements()) {
                uicontrol.getCryptoCurrencyAdapter().getElements().remove(string);
            }
            uicontrol.getCryptoCurrencyAdapter().notifyDataSetChanged();
            uicontrol.getCurrencyAdapter().notifyDataSetChanged();
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            uicontrol.getCurrencyAdapter().setMultiSelect(false);
            uicontrol.getCurrencyAdapter().getSelectedElements().clear();
            uicontrol.getCurrencyAdapter().notifyDataSetChanged();
            uicontrol.getCryptoCurrencyAdapter().setMultiSelect(false);
            uicontrol.getCryptoCurrencyAdapter().getSelectedElements().clear();
            uicontrol.getCryptoCurrencyAdapter().notifyDataSetChanged();
        }
    };
}
