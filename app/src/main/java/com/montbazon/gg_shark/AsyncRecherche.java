package com.montbazon.gg_shark;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//import org.json.simple.parser.JSONParser;


public class AsyncRecherche extends AsyncTask<String,Integer, String[]>
{

    Context ctx;

    Boolean resultatExist = false;


    public AsyncRecherche(Context ctx)
    {
        super();
        this.ctx = ctx;

    }

    @Override
    protected String[] doInBackground(String... params)
    {

        String[] resultat = new String[3];

        URL urls[] = new URL[3];


        String storeid;
        try{

            for(int i = 1; i<4;i++)
            {
                if(params[i] == "true")
                {
                    switch (i)
                    {
                        case 1:
                            storeid = "&storeID=1";
                            break;
                        case 2:
                            storeid = "&storeID=7";
                            break;
                        case 3:
                            storeid = "&storeID=25";
                            break;
                        default:
                            Log.i("Pierre", "this failed");
                            storeid = "";

                    }

                    //Création de l'url en fonction des préférences
                    urls[i-1] = new URL("https://www.cheapshark.com/api/1.0/deals?title="+params[0]+"&limit=300"+storeid+params[4]);

                }

            }

            for(int i =0;i<3;i++)
            {
                if(urls[i] != null)
                {
                    HttpURLConnection urlConnection = (HttpURLConnection) urls[i].openConnection();

                    try
                    {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());


                        resultat[i] = readStream(in);

                    }catch (Exception e) {
                        e.printStackTrace();
                    } finally{urlConnection.disconnect();
                    }
                }
                else
                {
                    resultat[i] = "";
                }


            }





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return resultat;


    }

    @Override
    protected void onPostExecute(String[] resultat)
    {

            //On passe directement à la list view des deals
            Intent deal = new Intent( ctx, DealListActivity.class);


            for(int i = 0;i<3;i++)
            {
                if (resultat[i]!="")
                {
                    deal.putExtra(Integer.toString(i),resultat[i]);
                }
                else
                {
                    deal.putExtra(Integer.toString(i),"");
                }


            }

            deal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(deal);






    }


    //Méthode de lecture de stream
    private String readStream(InputStream is)
    {

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return (String)bo.toString();
        } catch (IOException e) {
            return (String)"";
        }
    }





}
