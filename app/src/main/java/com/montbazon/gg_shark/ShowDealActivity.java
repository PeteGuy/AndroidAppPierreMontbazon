package com.montbazon.gg_shark;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowDealActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealview);

        /*goDeal.putExtra("name",deal.name);
        goDeal.putExtra("note",deal.note);
        goDeal.putExtra("storeID",deal.storeid);
        goDeal.putExtra("rating",deal.note);*/

        Bundle extras = getIntent().getExtras();

        ImageView imageStore = findViewById(R.id.img);

        String store = extras.getString("storeID");

        switch(store)
        {
            case "1":
                imageStore.setImageResource(R.drawable.steam);

                break;

            case "7":
                imageStore.setImageResource(R.drawable.gog);
                break;

            case "25":
                imageStore.setImageResource(R.drawable.epicgames);
                break;

            default:
                imageStore.setImageResource(R.drawable.whiteicon);
        }

        TextView titre = findViewById(R.id.titre);
        TextView prix = findViewById(R.id.prix);
        TextView prixOri = findViewById(R.id.prix2);
        TextView note = findViewById(R.id.note);




        titre.setText("Titre : "+extras.getString("name"));
        prix.setText("Prix du deal : "+extras.getString("prix") + "$");
        prixOri.setText("Prix original : "+extras.getString("prixOri") + "$");
        note.setText("Note : "+extras.getString("note")+"/10");


    }
}
