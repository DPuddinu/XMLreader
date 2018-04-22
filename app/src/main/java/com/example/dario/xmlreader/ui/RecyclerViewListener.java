package com.example.dario.xmlreader.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;

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
                uicontrol.sourceSetup(uicontrol.getCurrenciesList().get(position));
                uicontrol.hideRecyclerView();
                Log.e("avvenuto click","Single Click on position        :"+position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.e("avvenuto long click","Long Click on position        :"+position);
            }
        }));
    }


}
