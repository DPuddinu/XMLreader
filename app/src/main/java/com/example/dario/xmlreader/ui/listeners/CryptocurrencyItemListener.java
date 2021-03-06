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

public class CryptocurrencyItemListener {

    private UIcontrol uicontrol;
    private Context context;

    public CryptocurrencyItemListener(UIcontrol uicontrol, Context context) {
        this.uicontrol = uicontrol;
        this.context = context;
    }


    public void setListener(){

        //LISTENER TASTO FROM
        RecyclerTouchManager touchlistener = new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                if(!uicontrol.getCurrencyAdapter().isMultiSelect()){
                    String fullname=uicontrol.getCryptoCurrenciesList().get(position) ;
                    String id=CurrencyDB.getInstance().getShortName(fullname,CurrencyDB.getInstance().getCryptoCurrencyList());
                    Double value= CurrencyDB.getInstance().getCryptoCurrencyValue(fullname);

                    ChangesManager.getInstance().getOperation().setFrom(value);
                    ChangesManager.getInstance().getOperation().setNameFrom(fullname);

                    uicontrol.getModel().getTextViewFrom().setText(id);
                    uicontrol.hideRecyclerView(uicontrol.getModel().getRecyclerView1());
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
            }
        });

        uicontrol.getModel().getRecyclerView1().addOnItemTouchListener(touchlistener);
    }

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

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

            for (String string : uicontrol.getCryptoCurrencyAdapter().getSelectedElements()) {
                uicontrol.getCryptoCurrencyAdapter().getElements().remove(string);
            }
            uicontrol.getCryptoCurrencyAdapter().notifyDataSetChanged();

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            uicontrol.getCryptoCurrencyAdapter().setMultiSelect(false);
            uicontrol.getCryptoCurrencyAdapter().getSelectedElements().clear();
            uicontrol.getCryptoCurrencyAdapter().notifyDataSetChanged();
        }
    };
}
