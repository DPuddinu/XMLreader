package com.example.dario.xmlreader.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UImodel {

    private Button enter,from,to;
    private TextView textViewResults, textViewFrom,textViewTo,lastUpdate;
    private EditText amount;
    private RecyclerView recyclerView,recyclerView1;

    public RecyclerView getRecyclerView1() {
        return recyclerView1;
    }

    public void setRecyclerView1(RecyclerView recyclerView1) {
        this.recyclerView1 = recyclerView1;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setResults(String results){
        textViewResults.setText(results);
    }

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

    public TextView getTextViewResults() {
        return textViewResults;
    }

    public void setTextViewResults(TextView textViewResults) {
        this.textViewResults = textViewResults;
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
