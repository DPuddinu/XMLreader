package com.example.dario.xmlreader;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class SimpleRequest{

    private static String url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml?45e0ae28974a1e3ecd25b032e3a43104";

    public void doRequest(Context context, final VolleyCallback callback){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> callback.onSuccess(response), error -> {
                });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
