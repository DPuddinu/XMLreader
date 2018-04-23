package com.example.dario.xmlreader.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class RecyclerViewListener {
    private UIcontrol uicontrol;
    private Context context;


    public RecyclerViewListener(UIcontrol uicontrol, Context context) {
        this.uicontrol = uicontrol;
        this.context = context;
    }

    public void setListener(){

        uicontrol.getModel().getRecyclerView().addOnItemTouchListener(new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                if(!uicontrol.getRowAdapter().isMultiSelect()){
                    uicontrol.sourceSetup(uicontrol.getCurrenciesList().get(position));
                    uicontrol.hideRecyclerView();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
            }
        }));
    }


    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            uicontrol.getRowAdapter().setMultiSelect(true);
            menu.add("Delete");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            for (String string : uicontrol.getRowAdapter().getSelectedElements()) {
                uicontrol.getRowAdapter().getElements().remove(string);
            }
            uicontrol.getRowAdapter().notifyDataSetChanged();
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            uicontrol.getRowAdapter().setMultiSelect(false);
            uicontrol.getRowAdapter().getSelectedElements().clear();
            uicontrol.getRowAdapter().notifyDataSetChanged();
        }
    };

}
