package com.montbazon.gg_shark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity
{
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btest = findViewById(R.id.btest);

        btest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent deal = new Intent( getApplicationContext(),DealList.class);

                startActivity(deal);



            }
        });

        Button brequete = findViewById(R.id.bchercher);
        EditText inputTitre = findViewById(R.id.inputTitre);

        brequete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AsyncRecherche requete = new AsyncRecherche(getApplicationContext());
                requete.execute(inputTitre.getText().toString());


            }
        });


    }





    public static void goToDealList(Array array)
    {
        //Intent login = new Intent(getApplicationContext(), GiveLogin.class);startActivity(login);
    }
}