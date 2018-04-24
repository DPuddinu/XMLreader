package com.example.dario.xmlreader.ui.listeners;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dario.xmlreader.ChangesManager;
import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.ui.RecyclerTouchManager;
import com.example.dario.xmlreader.ui.UIcontrol;

public class CurrencyItemListener {

    private UIcontrol uicontrol;
    private Context context;

    public CurrencyItemListener(UIcontrol uicontrol, Context context) {
        this.uicontrol = uicontrol;
        this.context = context;
    }

    public void setListener(){

        //LISTENER TASTO TO
        RecyclerTouchManager touchlistener = new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                if(!uicontrol.getCurrencyAdapter().isMultiSelect()){
                    String fullname=uicontrol.getCurrenciesList().get(position) ;
                    String id=CurrencyDB.getInstance().getShortName(fullname,CurrencyDB.getInstance().getCurrencyList());
                    Double value= CurrencyDB.getInstance().getCurrencyValue(fullname);

                    ChangesManager.getInstance().getOperation().setTo(value);
                    ChangesManager.getInstance().getOperation().setNameTo(fullname);

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

    }

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            uicontrol.getCurrencyAdapter().setMultiSelect(true);
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

            uicontrol.getCurrencyAdapter().notifyDataSetChanged();
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            uicontrol.getCurrencyAdapter().setMultiSelect(false);
            uicontrol.getCurrencyAdapter().getSelectedElements().clear();
            uicontrol.getCurrencyAdapter().notifyDataSetChanged();
        }
    };
}
