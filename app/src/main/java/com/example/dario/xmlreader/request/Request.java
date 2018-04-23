package com.example.dario.xmlreader.request;

import android.content.Context;

public interface Request {
    void doRequest(Context context, final VolleyCallback callback);
}
