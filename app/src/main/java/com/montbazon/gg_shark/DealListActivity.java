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

    ArrayList<JSONArray> jlist;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Bundle extras = getIntent().getExtras();

        //On crée liste des éléments récupérés
        jlist = new ArrayList<JSONArray>();

        for (int i = 0;i<3;i++)
        {
            if(extras.getString(Integer.toString(i))!="")
            {
                try {
                    jlist.add(new JSONArray(extras.getString(Integer.toString(i))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }




        JSONArray deaList;
        try {

            ArrayList<Deal>  theDeals = new ArrayList<Deal>();

            DealAdapter adapter = new DealAdapter(this,theDeals);

            ListView list = (ListView)findViewById(R.id.maliste);


            //On parcourt les différents items de chaque élément de la liste
            for(JSONArray j : jlist )
            {
                for (int i=0; i<j.length(); i++) {
                    if(((String) j.getJSONObject(i).get("isOnSale")).equals("1"))
                    {
                        adapter.add(new Deal((String) j.getJSONObject(i).get("title"),
                                (String) j.getJSONObject(i).get("salePrice"),(String)j.getJSONObject(i).get("thumb"),
                                (String)j.getJSONObject(i).get("storeID"),(String)j.getJSONObject(i).get("dealRating"),
                                (String) j.getJSONObject(i).get("normalPrice")));
                    }


                }
            }

            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
}
