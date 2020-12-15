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

public class DealListActivity extends AppCompatActivity
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
