package com.example.dario.xmlreader;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RequestHandler {

    private RatesRequest ratesRequest = new RatesRequest();
    private Context context;

    public RequestHandler(Context context) {
        this.context = context;
    }

    public void setResponse(String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putString("Response", response);
        editor.apply();
    }

    public void fetchDocument(){

        ratesRequest.doRequest(context, string -> setResponse(string));
    }
}
