package com.example.dario.xmlreader;

import java.util.ArrayList;
import java.util.List;

public class ChangesManager {

    private static ChangesManager mInstance;

    public static synchronized ChangesManager getInstance() {
        if (mInstance == null) {
            mInstance = new ChangesManager();
        }
        return mInstance;
    }

    private List<Operation> operationsHistory = new ArrayList<>();
    private Operation operation = new Operation();

    public Operation getOperation() {
        return operation;
    }

    public void addToHistory(){
        operationsHistory.add(operation);

    }
}