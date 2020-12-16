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

    //Déclaration des variables

    String prix = "";
    //String storesid = "(";

    String prefUrl = "";
    String prixmax;


    TextView profil;
    Button brequete;

    EditText inputTitre;



    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        profil = findViewById(R.id.profil);
        brequete= findViewById(R.id.bchercher);

        inputTitre = findViewById(R.id.inputTitre);


        reloadPreferences();
        //Toast.makeText(getApplicationContext(),prefs.getString("nom",""),Toast.LENGTH_SHORT).show();


        brequete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("JFL", inputTitre.getText().toString()+prefUrl);
                AsyncRecherche requete = new AsyncRecherche(getApplicationContext());
                requete.execute(inputTitre.getText().toString(),"1");



            }
        });






    }

    public void goToPreferences(View v)
    {
        //Log.i("TENTEY", "therewasanattempt");
        Intent preferences = new Intent(getApplicationContext(),PreferencesActivity.class);
        startActivity(preferences);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();


        reloadPreferences();


    }

    public void reloadPreferences()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //prefUrl = "";


        /*storesid = "(";



        //Ajout des magasins selon les préférences
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

        prefUrl= "&storeID="+storesid;
        //Log.i("TENTEY", storesid);
        */


        //Ajout du prix max si on a une préférence
        prix = prefs.getString("prixmax","60");
        if(prix != null)
        {
            if (android.text.TextUtils.isDigitsOnly(prix)) {
                prixmax = "&upperPrice";
            }
        }


        profil.setText(prefs.getString("nom",""));

    }











}