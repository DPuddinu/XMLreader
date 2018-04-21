package com.example.dario.xmlreader;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class UImodel {

    private Button enter,from,to;
    private TextView results, textViewFrom,textViewTo,lastUpdate;
    private EditText amount;
    private ListView list1;


    public ListView getList1() {
        return list1;
    }

    public void setList1(ListView list1) {
        this.list1 = list1;
    }



    public ArrayList<String> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    private ArrayList<String> strings=new ArrayList<>();

    public TextView getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(TextView lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isAmountEmpty(){
        return amount.getText().toString().equals("");
    }



    public Button getEnter() {
        return enter;
    }

    public void setEnter(Button enter) {
        this.enter = enter;
    }

    public Button getFrom() {
        return from;
    }

    public void setFrom(Button from) {
        this.from = from;
    }

    public Button getTo() {
        return to;
    }

    public void setTo(Button to) {
        this.to = to;
    }

    public TextView getResults() {
        return results;
    }

    public void setResults(TextView results) {
        this.results = results;
    }

    public TextView getTextViewFrom() {
        return textViewFrom;
    }

    public void setTextViewFrom(TextView textViewFrom) {
        this.textViewFrom = textViewFrom;
    }

    public TextView getTextViewTo() {
        return textViewTo;
    }

    public void setTextViewTo(TextView textViewTo) {
        this.textViewTo = textViewTo;
    }

    public EditText getAmount() {
        return amount;
    }

    public void setAmount(EditText amount) {
        this.amount = amount;
    }
}
