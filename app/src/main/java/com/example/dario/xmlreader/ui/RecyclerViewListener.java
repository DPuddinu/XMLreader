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
    private boolean multiSelect = false;
    private ArrayList<String> selectedItems = new ArrayList<>();


    public RecyclerViewListener(UIcontrol uicontrol, Context context) {
        this.uicontrol = uicontrol;
        this.context = context;

    }
    public void setListener(){

        uicontrol.getModel().getRecyclerView().addOnItemTouchListener(new RecyclerTouchManager(context,
                uicontrol.getModel().getRecyclerView(), new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                if(!multiSelect){
                    uicontrol.sourceSetup(uicontrol.getCurrenciesList().get(position));
                    uicontrol.hideRecyclerView();
                }
                else
                    selectItem(view,uicontrol.getCurrenciesList().get(position));
                Log.e("avvenuto click","Single Click on position        :"+position);
            }

            @Override
            public void onLongClick(View view, int position) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
                    selectItem(view,uicontrol.getCurrenciesList().get(position));
                Log.e("avvenuto long click","Long Click on position        :"+position);
            }
        }));
    }
    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            multiSelect = true;
            menu.add("Delete");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            for (String intItem : selectedItems) {
                uicontrol.getCurrenciesList().remove(intItem);
            }
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            multiSelect = false;
            selectedItems.clear();
            uicontrol.getRowAdapter().notifyItemRangeChanged(0,uicontrol.getCurrenciesList().size());
        }
    };

    public void selectItem(View view,String string) {
        if (multiSelect) {
            if (selectedItems.contains(string)) {
                selectedItems.remove(string);
                view.setBackgroundColor(Color.LTGRAY);
            } else {
                selectedItems.add(string);
                view.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
