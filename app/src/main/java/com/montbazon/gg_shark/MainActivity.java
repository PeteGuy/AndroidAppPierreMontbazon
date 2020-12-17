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


    Boolean[] stores = new Boolean[3];

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



        brequete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("JFL", inputTitre.getText().toString()+prefUrl);
                Toast toast = Toast.makeText(getApplicationContext(), "Recherche de deals", Toast.LENGTH_SHORT);
                toast.show();
                AsyncRecherche requete = new AsyncRecherche(getApplicationContext());
                requete.execute(inputTitre.getText().toString(),stores[0].toString(),stores[1].toString(),stores[2].toString(),prixmax);




            }
        });






    }

    public void goToPreferences(View v)
    {

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
        //Toast.makeText(getApplicationContext(),"Chargement des préférences",Toast.LENGTH_SHORT).show();




        //Ajout des magasins selon les préférences
        stores[0]=(prefs.getBoolean("SteamSwitch",false));

        stores[1]=(prefs.getBoolean("GOGSwitch",false));

        stores[2] = prefs.getBoolean("EpicGamesSwitch",false);







        //Ajout du prix max si on a une préférence
        prix = prefs.getString("prixmax","60");
        if(prix != null)
        {
            if (android.text.TextUtils.isDigitsOnly(prix)) {
                prixmax = "&upperPrice="+prix;
            }

        }


        profil.setText(prefs.getString("nom",""));

    }











}