package com.example.dario.xmlreader;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser  {

    private SimpleRequest simpleRequest = new SimpleRequest();
    private Context context;

    public XmlParser(Context context) {
        this.context = context;
    }

    public void setResponse(String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putString("Response", response);
        editor.commit();
    }

    public void fetchDocument(){

        simpleRequest.doRequest(context, new VolleyCallback() {
            @Override
            public void onSuccess(String string) {
                setResponse(string);
            }
        });
    }
}
