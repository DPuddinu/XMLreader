package com.example.dario.xmlreader;

import android.view.MenuItem;
import android.widget.PopupMenu;

public class PopupMenuControl {

    private CurrencyControl currencyControl;

    public PopupMenuControl(CurrencyControl currencyControl) {
        this.currencyControl = currencyControl;
    }

    public void fetchData(final UIcontrol control, final ChangesCalculator changesCalculator) {

        for (int i = 0; i < currencyControl.getCurrencyList().size(); i++) {
            control.getModel().getMenu1().getMenu().add(currencyControl.getCurrencyList().get(i).getName());
            control.getModel().getMenu2().getMenu().add(currencyControl.getCurrencyList().get(i).getName());
        }

        control.getModel().getMenu1().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                control.getModel().getTextViewFrom().setText(item.getTitle().toString());
                changesCalculator.setFrom(currencyControl.getCurrencyValue(item.getTitle().toString()));


                return false;
            }
        });
        control.getModel().getMenu2().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                control.getModel().getTextViewTo().setText(item.getTitle().toString());
                changesCalculator.setTo(currencyControl.getCurrencyValue(item.getTitle().toString()));

                return false;
            }
        });

    }
}
