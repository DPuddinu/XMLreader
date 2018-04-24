package com.example.dario.xmlreader.request;


import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.CurrencyModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CryptoCurrencyIParser implements IParser {

    @Override
    public void parseDocument(String response){

        try {

            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String symbol = jsonObject.getString("symbol");
                String price = jsonObject.getString("price_eur");

                CurrencyModel model = new CurrencyModel(name,symbol,price);
                CurrencyDB.getInstance().addcryptoCurrency(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
