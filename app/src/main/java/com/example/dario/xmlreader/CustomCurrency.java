package com.example.dario.xmlreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomCurrency extends AppCompatActivity {

    private EditText fullName,shortName,rate;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_currency);

        fullName=findViewById(R.id.fullnameId);
        shortName=findViewById(R.id.shortnameId);
        rate=findViewById(R.id.rateId);
        submit=findViewById(R.id.submitId);

        submit.setOnClickListener((View v) -> {
            String fullname=fullName.getText().toString();
            String shortname=shortName.getText().toString();
            String temp=rate.getText().toString();

            CurrencyModel model = new CurrencyModel(fullname,shortname,temp);
            CurrencyDB.getInstance().addCurrency(model);

        });
    }
}
