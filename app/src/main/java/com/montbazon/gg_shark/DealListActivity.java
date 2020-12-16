package com.montbazon.gg_shark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DealListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Bundle extras = getIntent().getExtras();


        ArrayList<JSONArray> jlist = new ArrayList<JSONArray>();

        String s =extras.getString("r1");
        String s2 =extras.getString("r2");
        String s3 =extras.getString("r3");

        try {
            jlist.add(new JSONArray(s));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jlist.add(new JSONArray(s2));
        } catch (JSONException e) {
            e.printStackTrace();
        }try {
        jlist.add(new JSONArray(s3));
    } catch (JSONException e) {
        e.printStackTrace();
    }


        JSONArray deaList;
        try {
            //deaList = new JSONArray(s);

            ListView list = (ListView)findViewById(R.id.maliste);
            ArrayAdapter<String> tableau = new ArrayAdapter<String>(
                    list.getContext(), R.layout.montexte);
            for(JSONArray j : jlist )
            {
                for (int i=0; i<j.length(); i++) {
                    tableau.add((String) j.getJSONObject(i).get("title"));
                }
            }

            list.setAdapter(tableau);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent n = new Intent(getApplicationContext(), ShowDealActivity.class);
                    //n.putExtra("position", position);
                    startActivity(n);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
}
