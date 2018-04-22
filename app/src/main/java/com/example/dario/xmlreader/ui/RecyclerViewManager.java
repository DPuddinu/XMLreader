package com.example.dario.xmlreader.ui;

import android.view.View;

public class RecyclerViewManager {
    private UIcontrol uIcontrol;

    public RecyclerViewManager(UIcontrol uIcontrol) {
        this.uIcontrol = uIcontrol;
    }
    public void hideRecyclerView(){
        uIcontrol.getModel().getmRecyclerView().setVisibility(View.INVISIBLE);
    }

    public void showRecyclerView(){
        if(uIcontrol.getModel().getmRecyclerView().getVisibility()==View.VISIBLE)uIcontrol.getModel().getmRecyclerView().setVisibility(View.INVISIBLE);
        else uIcontrol.getModel().getmRecyclerView().setVisibility(View.VISIBLE);
    }

}
