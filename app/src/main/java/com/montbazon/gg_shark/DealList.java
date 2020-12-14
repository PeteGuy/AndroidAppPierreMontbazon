package com.montbazon.gg_shark;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DealList extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Bundle extras = getIntent().getExtras();
        String s =extras.getString("jsonarray");
        JSONArray deaList;
        try {
            deaList = new JSONArray(s);

            ListView list = (ListView)findViewById(R.id.maliste);
            ArrayAdapter<String> tableau = new ArrayAdapter<String>(
                    list.getContext(), R.layout.montexte);
            for (int i=0; i<deaList.length(); i++) {
                tableau.add((String) deaList.getJSONObject(i).get("external"));
            }
            list.setAdapter(tableau);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
