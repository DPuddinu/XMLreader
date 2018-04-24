package com.example.dario.xmlreader.request;

import android.content.Context;

public interface IRequest {
    void doRequest(Context context, final VolleyCallback callback);
}
