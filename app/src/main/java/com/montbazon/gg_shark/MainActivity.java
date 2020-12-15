package com.montbazon.gg_shark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity
{

    int prixmax;


    String prix = "";
    String storesid = "(";

    String prefUrl = "";


    TextView profil;



    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Toast.makeText(getApplicationContext(),prefs.getString("nom",""),Toast.LENGTH_SHORT).show();







        if(prefs.getBoolean("SteamSwitch",false))
        {
            if (storesid.substring(0, 1) != "(")
                storesid += ",";
            storesid += "1";
        }
        if(prefs.getBoolean("GOGSwitch",false))
        {
            if(storesid.substring(0,1) != "(")
                storesid+=",";
            storesid+="7";
        }
        if(prefs.getBoolean("EpicGamesSwitch",false))
        {
            if (storesid.substring(0, 1) != "(")
                storesid += ",";
            storesid += "25";

        }
        storesid+=")";
        //Log.i("TENTEY", storesid);




        prix = prefs.getString("prixmax",null);
        if(prix != null)
        {
            if (android.text.TextUtils.isDigitsOnly(prix)) {
                prixmax = Integer.parseInt(prix);
            }
        }

        prefUrl+= "&storeID="+storesid;

        profil = findViewById(R.id.profil);
        profil.setText(prefs.getString("nom",""));

        Button brequete = findViewById(R.id.bchercher);
        EditText inputTitre = findViewById(R.id.inputTitre);





        brequete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("JFL", inputTitre.getText().toString()+prefUrl);
                AsyncRecherche requete = new AsyncRecherche(getApplicationContext());
                requete.execute(inputTitre.getText().toString(),prefUrl);



            }
        });




    }

    public void goToPreferences(View v)
    {
        Log.i("TENTEY", "therewasanattempt");
        Intent preferences = new Intent(getApplicationContext(),PreferencesActivity.class);
        startActivity(preferences);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        prefUrl = "";
        storesid = "(";



        if(prefs.getBoolean("SteamSwitch",false))
        {
            if (storesid.substring(0, 1) != "(")
                storesid += ",";
            storesid += "1";
        }
        if(prefs.getBoolean("GOGSwitch",false))
        {
            if(storesid.substring(0,1) != "(")
                storesid+=",";
            storesid+="7";
        }
        if(prefs.getBoolean("EpicGamesSwitch",false))
        {
            if (storesid.substring(0, 1) != "(")
                storesid += ",";
            storesid += "1";

        }
        storesid+=")";
        //Log.i("TENTEY", storesid);




        prix = prefs.getString("prixmax",null);
        if(prix != null)
        {
            if (android.text.TextUtils.isDigitsOnly(prix)) {
                prixmax = Integer.parseInt(prix);
            }
        }


        TextView profil = findViewById(R.id.profil);
        profil.setText(prefs.getString("nom",""));




    }











}