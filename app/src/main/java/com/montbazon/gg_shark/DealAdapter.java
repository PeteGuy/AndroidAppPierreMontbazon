package com.montbazon.gg_shark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;



public class DealAdapter  extends ArrayAdapter<Deal> {
    public DealAdapter(Context context, ArrayList<Deal> theDeals) {
        super(context,0,theDeals);
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        Deal deal = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.mondeal,parent,false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        ImageView imageStore = (ImageView) convertView.findViewById(R.id.imageStore);
        Button button = (Button) convertView.findViewById(R.id.button);

        name.setText(deal.name);
        price.setText(deal.price);

        switch(deal.storeid)
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

        imageStore.getLayoutParams().height = 75;
        imageStore.getLayoutParams().width = 75;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDeal = new Intent(getContext(),ShowDealActivity.class);
                goDeal.putExtra("name",deal.name);
                goDeal.putExtra("note",deal.note);
                goDeal.putExtra("storeID",deal.storeid);
                goDeal.putExtra("thumb",deal.imageURL);
                goDeal.putExtra("prix",deal.price);
                goDeal.putExtra("prixOri",deal.priceOri);

                getContext().startActivity(goDeal);
            }
        });
        //Log.i("storeid",deal.storeid);
        return convertView;

    }
}
