package com.example.el_terminali;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ActiveOrderDetail {
    private String odatametadata;
    ArrayList<Object> value = new ArrayList<Object>();


    // Getter Methods

    public String getOdatametadata() {
        return odatametadata;
    }

    // Setter Methods

    public void setOdatametadata(String odatametadata) {
        this.odatametadata = odatametadata;
    }

    public void Test() {
        Gson gson = new Gson();
        String s = gson.toJson(value);
        Log.i("BERAT", s);
    }
}
