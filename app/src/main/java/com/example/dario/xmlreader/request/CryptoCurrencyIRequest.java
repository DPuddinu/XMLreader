package com.example.dario.xmlreader.request;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class CryptoCurrencyIRequest implements IRequest {

    private static String url = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR";

    public void doRequest(Context context, final VolleyCallback callback){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                callback::onSuccess, error -> {
        });
        RequestManager.getInstance(context).addToRequestQueue(stringRequest);
    }
}
